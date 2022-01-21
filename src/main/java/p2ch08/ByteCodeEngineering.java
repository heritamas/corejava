package p2ch08;

import org.apache.bcel.Repository;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;
import org.apache.bcel.generic.ClassGen;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.InstructionFactory;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.LDC;
import org.apache.bcel.generic.MethodGen;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ByteCodeEngineering {

    public static void main(String[] args) throws Exception {

        JavaClass clazz = Repository.lookupClass("p2ch08.Targeted");

        ClassGen classGen = new ClassGen(clazz);
        ConstantPoolGen constantPoolGen = new ConstantPoolGen(clazz.getConstantPool());
        InstructionFactory fact = new InstructionFactory(classGen, constantPoolGen);

        Method[] methodArray = clazz.getMethods();
        Stream<Integer> counter = IntStream.iterate(0, i -> i + 1).boxed();
        Iterator<Integer> iterator = counter.iterator();
        AbstractMap.SimpleEntry<Integer, Method> greetMethodInfo = Arrays.stream(methodArray)
                .map(method -> {
                    return new AbstractMap.SimpleEntry<>(iterator.next(), method);
                })
                .filter(pair -> pair.getValue().getName().equals("greet"))
                .findFirst()
                .get();

        Method greetMethod = greetMethodInfo.getValue();
        Integer position = greetMethodInfo.getKey();

        MethodGen mg = new MethodGen(greetMethod, "p2ch08.Targeted", constantPoolGen);
        InstructionList il = new InstructionList();
        String msg = "I'm here";
        il.append(new LDC(constantPoolGen.addString(msg)));
        il.append(fact.createPrintln(msg));
        mg.getInstructionList().insert(il);
        mg.setMaxLocals();
        mg.setMaxStack();
        Method morphedMethod = mg.getMethod();
        il.dispose();

        classGen.setMethodAt(morphedMethod, position);

        System.out.println("********Constant Pool**********");
        System.out.println(constantPoolGen.getFinalConstantPool());

        JavaClass morphedJavaClass = classGen.getJavaClass();
        morphedJavaClass.dump("p2ch08/Targeted.class");

    }

}
