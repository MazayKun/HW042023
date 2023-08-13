package ru.mikheev.kirill.hw11log.asm.scanner;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

class LogAnnotationClassScanner extends ClassVisitor {

    private static final String LOG_ANNOTATIONS_MARKER = 'L' + LogAnnotationMarker.class.getName().replace('.', '/') + ';';

    private boolean logAnnotationsAlreadyProcessed = false;

    public LogAnnotationClassScanner(ClassVisitor cv) {
        super(Opcodes.ASM9, cv);
    }

    @Override
    public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
        if (LOG_ANNOTATIONS_MARKER.equals(descriptor)) {
            logAnnotationsAlreadyProcessed = true;
        }
        return super.visitAnnotation(descriptor, visible);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        if ( ! logAnnotationsAlreadyProcessed) {
            MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
            return new LogMethodParametersGenerator(mv, access, name, descriptor);
        }
        return super.visitMethod(access, name, descriptor, signature, exceptions);
    }

    @Override
    public void visitEnd() {
        if ( ! logAnnotationsAlreadyProcessed) {
            visitAnnotation(LOG_ANNOTATIONS_MARKER, true);
        }
        super.visitEnd();
    }
}
