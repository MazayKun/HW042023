package ru.mikheev.kirill.hw11log.asm.scanner;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.GeneratorAdapter;

class LogMethodParametersGenerator extends GeneratorAdapter {

    private static final String LOG_ANNOTATION_NAME = 'L' + Log.class.getName().replace('.', '/') + ';';
    private static final String STRING_TYPE_DESCRIPTOR = "(Ljava/lang/String;)V";

    private final Type[] argTypes;
    private final String methodName;
    private final boolean methodStatic;

    private boolean isLogAnnotated = false;

    public LogMethodParametersGenerator(MethodVisitor delegate, int access, String name, String desc) {

        super(Opcodes.ASM5, delegate, access, name, desc);
        this.argTypes = Type.getArgumentTypes(desc);
        this.methodName = name;
        this.methodStatic = (access & Opcodes.ACC_STATIC) != 0;
    }

    @Override
    public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
        if (LOG_ANNOTATION_NAME.equals(descriptor)) {
            isLogAnnotated = true;
        }
        return super.visitAnnotation(descriptor, visible);
    }

    @Override
    public void visitCode() {
        if(isLogAnnotated) {
            printlnObject(
                    () -> visitLdcInsn("executed method: " + methodName),
                    STRING_TYPE_DESCRIPTOR
            );

            for(int i = 0; i < argTypes.length; i++) {
                Type argType = argTypes[i];
                int currIndex = i + 1;

                printObject(
                        () -> visitLdcInsn(currIndex  + " param with type " + argType.getClassName() + ": "),
                        STRING_TYPE_DESCRIPTOR
                );

                TypeInfo argTypeInfo = getTypeInfo(argType);
                printlnObject(
                        () -> visitVarInsn(argTypeInfo.loadOpcode, methodStatic ? currIndex - 1 : currIndex),
                        "(" + argTypeInfo.descriptor + ")V"
                );
            }

        }
        super.visitCode();
    }

    private void printObject(Runnable objectLoader, String typeDescriptor) {
        showObject(objectLoader, typeDescriptor, "print");
    }

    private void printlnObject(Runnable objectLoader, String typeDescriptor) {
        showObject(objectLoader, typeDescriptor,"println");
    }

    private void showObject(Runnable objectLoader, String typeDescriptor, String method) {
        visitFieldInsn(Opcodes.GETSTATIC,
                "java/lang/System", "out", "Ljava/io/PrintStream;");
        objectLoader.run();
        visitMethodInsn(Opcodes.INVOKEVIRTUAL,
                "java/io/PrintStream", method, typeDescriptor, false);
    }

    private TypeInfo getTypeInfo(Type type) {
        if(type.getDescriptor().length() == 1) {
            int loadOpcode;
            String descriptor;
            switch (type.getDescriptor().charAt(0)) {
                case 'I', 'C', 'Z': loadOpcode = Opcodes.ILOAD; descriptor = type.getDescriptor(); break;
                case 'B', 'S': loadOpcode = Opcodes.ILOAD; descriptor = "I"; break;
                case 'L': loadOpcode = Opcodes.LLOAD; descriptor = type.getDescriptor(); break;
                case 'F': loadOpcode = Opcodes.FLOAD; descriptor = type.getDescriptor(); break;
                case 'D': loadOpcode = Opcodes.DLOAD; descriptor = type.getDescriptor(); break;
                default : throw new UnsupportedOperationException("Не найден дескриптор операции загрузки для простого типа " + type.getClassName());
            }
            return new TypeInfo(loadOpcode, descriptor);
        }else{
            return new TypeInfo(Opcodes.ALOAD, "Ljava/lang/Object;");
        }
    }

    private record TypeInfo(int loadOpcode, String descriptor) {}
}
