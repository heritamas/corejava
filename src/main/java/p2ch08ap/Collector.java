package p2ch08ap;

import p2ch08.Collect;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.ElementScanner9;
import javax.tools.Diagnostic;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@SupportedAnnotationTypes("p2ch08.Collect")
@SupportedSourceVersion(SourceVersion.RELEASE_11)
public class Collector extends AbstractProcessor {

    static class DebugMethodVisitor extends ElementScanner9<Boolean, List<String>> {

        @Override
        public Boolean visitExecutable(ExecutableElement e, List<String> nfo) {
            if (e.getKind() == ElementKind.METHOD) {
                Name simpleName = e.getSimpleName();
                List<? extends VariableElement> parameters = e.getParameters();
                String params = parameters.stream()
                        .map(variableElement -> String.format("%s: %s", variableElement.getSimpleName(), variableElement.asType()))
                        .collect(Collectors.joining(", "));
                nfo.add(String.format("Method: %s, method parameters: %s", simpleName, params));
            }
            return true;
        }
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        Set<? extends Element> elts = roundEnv.getElementsAnnotatedWith(Collect.class);
        elts
                .stream()
                .filter(elt -> elt.getKind().equals(ElementKind.CLASS))
                .forEach(this::processElement);

        return true;
    }


    boolean processElement(Element elt) {

        try {
            String packageName = "";
            Element possiblePackage = elt.getEnclosingElement();
            if (possiblePackage.getKind() == ElementKind.PACKAGE) {
                packageName = possiblePackage.toString();
            }
            System.out.println("Package name: " + packageName);
            String className = elt.getSimpleName().toString();
            String fileName = String.format("%s-report", className);
            FileObject resource = processingEnv.getFiler().createResource(StandardLocation.CLASS_OUTPUT, packageName, fileName);
            System.out.println(resource.toUri());

            List<String> info = new ArrayList<>();
            DebugMethodVisitor dmv = new DebugMethodVisitor();
            dmv.scan(elt, info);
            
            try (PrintWriter printWriter = new PrintWriter(resource.openWriter())) {
                info.forEach(printWriter::println);
            }

        } catch (IOException e) {
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, e.getMessage());
            }

        return true;
    }

}
