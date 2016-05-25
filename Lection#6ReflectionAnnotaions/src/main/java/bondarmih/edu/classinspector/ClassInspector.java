package bondarmih.edu.classinspector;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by bondarm on 22.05.16.
 */
public class ClassInspector {

    public static Field[] getAnnotatedField(Class clazz, Class<? extends Annotation> annotationClass) {
        Field[] classFields = getFields(clazz);
        List<Field> annotatedClassFields = new LinkedList<Field>();
        for (Field currentField: classFields) {
            if (currentField.isAnnotationPresent(annotationClass))
                annotatedClassFields.add(currentField);
        }
        return annotatedClassFields.toArray(new Field[annotatedClassFields.size()]);
    }

    public static Field[] getFields(Class clazz) {
        List<Field> classFields = new LinkedList<Field>();
        Field[] declaredClassFields = clazz.getDeclaredFields();
        Collections.addAll(classFields, declaredClassFields);

        Class parent = clazz.getSuperclass();

        if (parent != null) {
            Field[] declaredSuperClassField = getFields(parent);

            if (declaredSuperClassField.length>0)
                Collections.addAll(classFields, declaredSuperClassField);
        }
        return classFields.toArray( new Field[classFields.size()]);
    }
}
