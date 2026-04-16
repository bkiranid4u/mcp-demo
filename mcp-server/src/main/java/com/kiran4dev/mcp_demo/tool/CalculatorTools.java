package com.kiran4dev.mcp_demo.tool;

import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Component;

@Component
public class CalculatorTools {

    @McpTool(name = "add", description = "Add two numbers together")
    public int add(
            @McpToolParam(description = "First number", required = true) int a,
            @McpToolParam(description = "Second number", required = true) int b) {
        return a + b;
    }

    @McpTool(name = "subtract", description = "Subtract two numbers")
    public double subtract(
            @McpToolParam(description = "First number", required = true) double a,
            @McpToolParam(description = "Second number", required = true) double b) {
        return a - b;
    }

    @McpTool(name = "multiply", description = "Multiply two numbers")
    public double multiply(
            @McpToolParam(description = "First number", required = true) double a,
            @McpToolParam(description = "Second number", required = true) double b) {
        return a * b;
    }

    @McpTool(name = "divide", description = "Divide two numbers")
    public double divide(
            @McpToolParam(description = "Dividend", required = true) double dividend,
            @McpToolParam(description = "Divisor", required = true) double divisor) {
        if (divisor == 0) {
            throw new IllegalArgumentException("Division by zero");
        }
        return dividend / divisor;
    }

    // @McpTool(name = "calculate-expression",
    //          description = "Calculate a complex mathematical expression")
    // public CallToolResult calculateExpression(
    //         CallToolRequest request,
    //         McpSyncRequestContext context) {

    //     Map<String, Object> args = request.arguments();
    //     String expression = (String) args.get("expression");

    //     // Use convenient logging method
    //     context.info("Calculating: " + expression);

    //     try {
    //         double result = evaluateExpression(expression);
    //         return CallToolResult.builder()
    //             .addTextContent("Result: " + result)
    //             .build();
    //     } catch (Exception e) {
    //         return CallToolResult.builder()
    //             .isError(true)
    //             .addTextContent("Error: " + e.getMessage())
    //             .build();
    //     }
    // }


    @McpTool(name = "calculate-area",
         description = "Calculate the area of a rectangle",
         annotations = @McpTool.McpAnnotations(
             title = "Rectangle Area Calculator",
             readOnlyHint = true,
             destructiveHint = false,
             idempotentHint = true
         ))
    public String calculateRectangleArea(
            @McpToolParam(description = "Width", required = true) double width,
            @McpToolParam(description = "Height", required = true) double height) {

        return String.format("Area: %.2f %s", width * height, "square units");
    }
}
