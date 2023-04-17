package util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Util classes for tests.
 * All methods are static.
 * Any tests involving inspecting the raw class are located here.
 * Not test file should create Class, Field, or Method objects except this one.
 * 
 * @author Willow Sapphire
 * @version 04/07/2023
 */
public class ClassInspections
{
    /**
     * Checks the modifiers for the fields of a class.
     * 
     * @param className the class to check.
     * @param fieldNames the names of the required fields.
     * @param isPublic true for public fields, false otherwise.
     *      indices must match with fieldNames.
     * @param isPrivate true for private fields, false otherwise.
     *      indices must match with fieldNames.
     * @param isProtected true for protected fields, false otherwise.
     *      indices must match with fieldNames.
     * @param isStatic true for static fields, false otherwise.
     *      indices must match with fieldNames.
     * @param isFinal true for final fields, false otherwise.
     *      indices must match with fieldNames.
     * @throws ClassNotFoundException if the class cannot be found.
     */
    public static void testFieldDeclarations(String className,
        String[] fieldNames, boolean[] isPublic, boolean isPrivate[],
        boolean[] isProtected, boolean[] isStatic, boolean[] isFinal)
    {
        try {
            @SuppressWarnings("rawtypes")
            Class deck = Class.forName(className);
            Field[] fields = deck.getDeclaredFields();
            for (Field f : fields)
            {
                int index = -1;
                for (int i = 0; i < fields.length; i++)
                {
                    if (fieldNames[i].equals(f.getName()))
                    {
                        index = i;
                        break;
                    }
                }
                if (index == -1)
                {
                    fail(String.format("Could not find field: %s", f.getName()));
                }
                else
                {
                    checkModifiers(f.getModifiers(), f.getName(), isPublic[index],
                        isProtected[index], isPrivate[index], isStatic[index], isFinal[index]);
                }
            }
        } catch (ClassNotFoundException e) {
            fail(String.format("Could not find the class: %s", className));
        }
    }

    /**
     * Checks that a given class has the correct modifiers.
     * 
     * @param className the class to check
     * @param isPublic true if the class should be public
     * @param isProtected true if the class should be protected
     * @param isPrivate true if the class should be private
     */
    public static void checkClassModifier(String className, boolean isPublic,
        boolean isProtected, boolean isPrivate)
    {
        try {
            @SuppressWarnings("rawtypes")
            Class deck = Class.forName(className);
            int modifiers = deck.getModifiers();
            assertEquals(isPublic, Modifier.isPublic(modifiers),
                String.format("Class: %s should %sbe public", className,
                    Modifier.isPublic(modifiers) ? "not " : ""));
            assertEquals(isProtected, Modifier.isProtected(modifiers));
                String.format("Class: %s should %sbe public", className,
                    Modifier.isPublic(modifiers) ? "not " : "");
            assertEquals(isPrivate, Modifier.isPrivate(modifiers));
                String.format("Class: %s should %sbe public", className,
                    Modifier.isPublic(modifiers) ? "not " : "");
        } catch (ClassNotFoundException e) {
            fail(String.format("Could not find the class: %s", className));
        }
    }

    /**
     * Checks that the modifiers for a particular field are correct.
     * Helper method.
     * 
     * @param modifier the modifier int for this field
     * @param fieldName the name of this field
     * @param isPublic whether or not the field should be public
     * @param isProtected whether or not the field should be protected
     * @param isPrivate whether or not the field should be private
     * @param isStatic whether or not the field should be static
     * @param isFinal whether or not the field should be final
     */
    private static void checkModifiers(int modifier, String fieldName, boolean isPublic,
        boolean isProtected, boolean isPrivate, boolean isStatic, boolean isFinal)
    {
        assertEquals(isPublic, Modifier.isPublic(modifier),
            String.format("Field: %s should %sbe public", fieldName,
                Modifier.isPublic(modifier) ? "not " : ""));

        assertEquals(isProtected, Modifier.isProtected(modifier),
            String.format("Field: %s should %sbe public", fieldName,
                Modifier.isProtected(modifier) ? "not " : ""));

        assertEquals(isPrivate, Modifier.isPrivate(modifier),
            String.format("Field: %s should %sbe private", fieldName,
                Modifier.isPrivate(modifier) ? "not " : ""));

        assertEquals(isStatic, Modifier.isStatic(modifier),
            String.format("Field: %s should %sbe static", fieldName,
                Modifier.isStatic(modifier) ? "not " : ""));

        assertEquals(isFinal, Modifier.isFinal(modifier),
            String.format("Field: %s should %sbe private", fieldName,
                Modifier.isFinal(modifier) ? "not " : ""));
    }
}
