package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        String [] parts = signatureString.split("\\s+");

        String accessModifier = parts.length > 1 ? parts[0] : null;
        String returnType = parts.length > 2 ? parts[1] : null;
        String methodNameWithArgs = parts.length > 3 ? parts[2] : null;

        String methodName = methodNameWithArgs != null ? methodNameWithArgs.split("\\(")[0] : null;
        String argumentsString = methodNameWithArgs != null ? methodNameWithArgs.split("\\(")[1].replace(")", "") : null;
        List<MethodSignature.Argument> arguments = parseArguments(argumentsString);

        return new MethodSignature(methodName, arguments);
    }

    private List<MethodSignature.Argument> parseArguments(String argumentsString) {
        List<MethodSignature.Argument> arguments = new ArrayList<>();

        if (argumentsString != null && !argumentsString.isEmpty()) {
            String[] argParts = argumentsString.split(",");
            for (String argPart : argParts) {
                String[] argComponents = argPart.trim().split("\\s+");
                if (argComponents.length == 2) {
                    String argType = argComponents[0];
                    String argName = argComponents[1];
                    arguments.add(new MethodSignature.Argument(argType, argName));
                }
            }
        }

        return arguments;
    }
}
