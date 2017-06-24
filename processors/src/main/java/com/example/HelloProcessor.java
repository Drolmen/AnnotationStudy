package com.example;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

@AutoService(Processor.class)
public class HelloProcessor extends AbstractProcessor {
    private Filer filer;    //Filter 是个接口，支持通过注解处理器创建新文件

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        filer = processingEnvironment.getFiler();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnv) {
        System.out.println("---------->");
        for (TypeElement typeElement : set) {

            for (Element element : roundEnv.getElementsAnnotatedWith(typeElement)) {
                if (element.getKind() == ElementKind.CLASS) {
                    TypeElement userTypeElement = (TypeElement) element;    //获取使用类相关信息
                    if (typeElement.getQualifiedName().toString().equals(HelloAnnotation.class.getCanonicalName())) {
                        // 创建一个_Created_By_Annotation带后缀的类
                        TypeSpec helloWorld = TypeSpec.classBuilder(userTypeElement.getSimpleName()+"_Created_By_Annotation")
                                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                                .build();
                        try {
                            String packageStr = userTypeElement.getQualifiedName().toString().replace("."+userTypeElement.getSimpleName().toString(), "");
                            JavaFile javaFile = JavaFile.builder(packageStr, helloWorld)
                                    .addFileComment(" This codes are generated automatically. Do not modify!")
                                    .build();
                            //　生成文件
                            javaFile.writeTo(filer);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        return true;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotataions = new LinkedHashSet<>();
        annotataions.add(HelloAnnotation.class.getCanonicalName());
        return annotataions;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latest();
    }
}
