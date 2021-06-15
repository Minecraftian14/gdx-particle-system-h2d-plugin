package com.alyrow.gdx.particle.view.gen;

import com.alyrow.gdx.particle.rules.ParticleEmissionDuration;
import com.alyrow.gdx.particle.rules.ParticleLife;
import com.alyrow.gdx.particle.utilities.EasyParsers;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextField;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.function.Function;

public class FieldsHunterTable<Type> extends VisTable {

    public static final ObjectMap<Class<?>, ObjectValue<?>> compatibleTypes = new ObjectMap<>();

    static {
        compatibleTypes.put(boolean.class, new ObjectValue<>(false, s -> EasyParsers.parse(s, false)));
        compatibleTypes.put(byte.class, new ObjectValue<>((byte) 0, s -> EasyParsers.parse(s, (byte) 0)));
        compatibleTypes.put(char.class, new ObjectValue<>((char) 0, s -> EasyParsers.parse(s, (char) 0)));
        compatibleTypes.put(short.class, new ObjectValue<>((short) 0, s -> EasyParsers.parse(s, (short) 0)));
        compatibleTypes.put(int.class, new ObjectValue<>(0, s -> EasyParsers.parse(s, 0)));
        compatibleTypes.put(float.class, new ObjectValue<>(0f, s -> EasyParsers.parse(s, 0f)));
        compatibleTypes.put(long.class, new ObjectValue<>(0L, s -> EasyParsers.parse(s, 0L)));
        compatibleTypes.put(double.class, new ObjectValue<>(0., s -> EasyParsers.parse(s, 0.)));
        compatibleTypes.put(String.class, new ObjectValue<>("null", s -> EasyParsers.parse(s, "error")));
        compatibleTypes.put(ParticleEmissionDuration.class, new ObjectValue<>(0, s -> {
            try {
                return new ParticleEmissionDuration(Float.parseFloat(s));
            } catch (Exception e) {
                return new ParticleEmissionDuration(EasyParsers.parse(s, true));
            }
        }));
    }

    protected static final String IGNORE_FIELD = "IGNORED";

    private Constructor<?>[] constructors;
    private Constructor<Type> constructor;
    private Class<?>[] paramClasses;
    private Object[] params;

    private Array<Method> methods = new Array<>();
    private Array<Class<?>> methodParamClasses = new Array<>();
    private Array<Object> methodParams = new Array<>();

    public FieldsHunterTable(Class<Type> clazz) throws NoSuchMethodException {
        pad(10);
    }

    public void addConstructor(Constructor<Type> constructor, String... paramNames) {
        Class<?>[] paramClasses = constructor.getParameterTypes();
        if (!isTypeInstantiatable(paramClasses))
            System.out.println("ERROR: The given constructor can not be initialized.");
        ;

        Parameter[] parameters = constructor.getParameters();
        Object[] params = instantiate(paramClasses);

        for (int i = 0; i < params.length; i++) {

            String name = params[i].toString();

            VisLabel label = new VisLabel(paramNames[i]);
            VisTextField field = new VisTextField(name);

            if (name.equals(IGNORE_FIELD)) {
                label.setVisible(false);
                field.setVisible(false);
            }

            add(label).growX();
            add(field).growX();
            row();
        }

        this.constructor = constructor;
        this.paramClasses = paramClasses;
        this.params = params;
    }

    public void addMethod(Method method, String name) {
        if (method.getParameterCount() != 1)
            System.out.println("ERROR: Bruh! Only setters can be given as Methods!");
        else if (!isTypeInstantiatable(method.getParameterTypes()))
            System.out.println("ERROR: We cant instantiate the parameters as required by " + method);

        methods.add(method);
        Class<?> parameterType = method.getParameterTypes()[0];
        methodParamClasses.add(parameterType);
        Object param = instantiate(parameterType)[0];
        methodParams.add(param);

        add(new VisLabel(name)).growX();
        add(new VisTextField(param.toString())).growX();
        row();
    }


    public static boolean isTypeInstantiatable(Class<?>[] paramClasses) {
        for (Class<?> paramClass : paramClasses)
            if (!compatibleTypes.containsKey(paramClass))
                return false;
        return true;
    }

    public static Object[] instantiate(Class<?>... paramClasses) {
        Object[] objects = new Object[paramClasses.length];
        for (int i = 0; i < paramClasses.length; i++)
            objects[i] = compatibleTypes.get(paramClasses[i]).defaultValue;
        return objects;
    }

    private void tableFieldsToParams(Class<?>[] paramClasses) {

        int i = 0;
        for (int k = 0; i < getChildren().size && k < paramClasses.length; i++) {
            Actor actor = getChildren().get(i);
            if (!(actor instanceof VisTextField)) continue;

            VisTextField field = (VisTextField) actor;
            if (field.isVisible())
                params[k] = textToObject(paramClasses[k], field.getText());
            else params[k] = compatibleTypes.get(paramClasses[k]).defaultValue;

            k++;
        }

        for (int k = 0; i < getChildren().size && k < methodParamClasses.size; i++) {
            Actor actor = getChildren().get(i);
            if (!(actor instanceof VisTextField)) continue;

            methodParams.set(k, textToObject(methodParamClasses.get(k), ((VisTextField) actor).getText()));

            k++;
        }

    }

    public static Object textToObject(Class<?> paramClass, String text) {
        return compatibleTypes.get(paramClass).parser.apply(text);
    }

    public Type getInstance() throws InvocationTargetException, InstantiationException, IllegalAccessException {
        tableFieldsToParams(paramClasses);
        Type type = constructor.newInstance(params);
        for (int i = 0; i < methods.size; i++) methods.get(i).invoke(type, methodParams.get(i));
        return type;
    }

    public record ObjectValue<Value>(Value defaultValue, Function<String, Value> parser) {
    }

}


//     private void createAllTheFieldsFromConstructor(Class<Type> clazz) throws NoSuchMethodException {
//
//        constructors = clazz.getConstructors();
//        Arrays.sort(constructors, (o1, o2) -> o2.getParameterCount() - o1.getParameterCount());
//
//        for (Constructor<?> constructor : constructors) {
//
//            Class<?>[] paramClasses = constructor.getParameterTypes();
//            Parameter[] parameters = constructor.getParameters();
//
//            if (!canWeInstantiateTheseParameters(paramClasses)) continue;
//
//            Object[] params = thenPleaseInstantiateAllTheseParameters(paramClasses);
//
//            System.out.println(params.length);
//            for (int i = 0; i < params.length; i++) {
//                add(new VisLabel(parameters[i].getName())).growX();
//                add(new VisTextField(params[i].toString())).growX();
//                row();
//            }
//
//            this.constructor = constructor;
//            this.paramClasses = paramClasses;
//            this.params = params;
//            return;
//        }
//
//        throw new NoSuchMethodException("There must be at least one public constructor with only primitive types as arguments in " + clazz.getSimpleName() + ".");
//
//    }
//
//
//
//
//
//
//
//        private void createAllTheFieldsFromMethods(Class<Type> clazz, Method[] _methods) throws NoSuchMethodException {
//
//        for (Method method : _methods)
//            if (method.getParameterCount() != 1)
//                throw new NoSuchMethodException("Bruh! Only setters can be given as Methods!");
//            else if (!isTypeInstantiatable(method.getParameterTypes()))
//                throw new NoSuchMethodException("We cant instantiate the parameters as required by " + method);
//
//        methods = _methods;
//        methodParamClasses = new Class[methods.length];
//        methodParams = new Object[methods.length];
//
//        for (int i = 0, methodsLength = methods.length; i < methodsLength; i++) {
//            Method method = methods[i];
//
//            methodParamClasses[i] = method.getParameterTypes()[0];
//            methodParams[i] = thenPleaseInstantiateAllTheseParameters(method.getParameterTypes())[0];
//
//            String name = method.getName();
//            if (name.startsWith("set")) name = name.substring(3);
//            name = CUD.sentenceConversion(name);
//
//            add(new VisLabel(name)).growX();
//            add(new VisTextField(methodParams[i].toString())).growX();
//            row();
//        }
//
//    }



