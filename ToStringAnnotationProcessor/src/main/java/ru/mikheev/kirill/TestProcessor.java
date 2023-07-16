package ru.mikheev.kirill;

import com.google.auto.service.AutoService;

import javax.annotation.processing.*;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import java.util.Set;

@AutoService(Processor.class)
@SupportedAnnotationTypes("ru.mikheev.kirill.ToString")
public class TestProcessor extends AbstractProcessor {
    @Override
    public synchronized void init(ProcessingEnvironment env) {
        super.init(env);
        System.out.println("INIT");
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        System.out.println("PROCESS START");
        for (Element annotatedElement : roundEnvironment.getElementsAnnotatedWith(ToString.class)) {
            System.out.println("----> " + annotatedElement.getSimpleName());
            if (annotatedElement.getKind() != ElementKind.CLASS) {
                throw new RuntimeException("Only classes can be annotated with @DecompositionAnnotation");
            }
            TypeElement typeElement = (TypeElement) annotatedElement;

            System.out.println(typeElement.getQualifiedName());
        }

        System.out.println("PROCESS END");
        return true;
    }
}
