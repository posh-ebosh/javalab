package ru.itis.javalab;

import com.google.auto.service.AutoService;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@AutoService(Processor.class)
@SupportedAnnotationTypes(value = {"ru.itis.javalab.HtmlForm"})
public class HtmlProcessor extends AbstractProcessor {

    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        // получить типы с аннотацией HtmlForm
        Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(HtmlForm.class);
        for (Element element : annotatedElements) {
            processingEnv.getMessager().printMessage(Diagnostic.Kind.MANDATORY_WARNING, "Process class: " + element.getSimpleName());
            String path = HtmlProcessor.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            path = path.substring(1) + element.getSimpleName().toString() + ".html";
            Path out = Paths.get(path);

            try {
                Map<String, Object> templateData = new HashMap<>();
                HtmlForm annotation = element.getAnnotation(HtmlForm.class);

                List<Map<String, String>> inputDataList = new ArrayList<>();
                for (Element enclosedElement : element.getEnclosedElements()) {
                    HtmlInput htmlInput = enclosedElement.getAnnotation(HtmlInput.class);
                    if (htmlInput == null) continue;
                    processingEnv.getMessager().printMessage(Diagnostic.Kind.MANDATORY_WARNING, "Process field: " + enclosedElement.getSimpleName());

                    Map<String, String> inputData = new HashMap<>();
                    inputData.put("type", htmlInput.type());
                    inputData.put("name", htmlInput.name());
                    inputData.put("placeholder", htmlInput.placeholder());
                    inputDataList.add(inputData);
                }
                templateData.put("method", annotation.action());
                templateData.put("action", annotation.method());
                templateData.put("inputs", inputDataList);

                Configuration configuration = new Configuration(Configuration.VERSION_2_3_21);
                configuration.setTemplateLoader(new ClassTemplateLoader(getClass(), "/"));
                Template template = configuration.getTemplate("template.ftl");

                BufferedWriter writer = new BufferedWriter(new FileWriter(out.toFile()));
                template.process(templateData, writer);
                writer.close();
            } catch (IOException | TemplateException e) {
                throw new IllegalArgumentException(e);
            }
        }
        return true;
    }
}