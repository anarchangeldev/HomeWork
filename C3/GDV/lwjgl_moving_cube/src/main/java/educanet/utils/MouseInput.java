package educanet.utils;

import org.joml.Vector2d;
import org.joml.Vector2f;

import static org.lwjgl.glfw.GLFW.*;

public class MouseInput {

    public static final float MOUSE_SENSITIVITY = 10f;


    private final Vector2d previousPos;
    private final Vector2d currentPos;
    private final Vector2f displayVec;

    private boolean inWindow = false;
    private boolean leftButtonPressed = false;
    private boolean rightButtonPressed = false;

    public MouseInput() {
        previousPos = new Vector2d(-1, -1);
        currentPos = new Vector2d(0, 0);
        displayVec = new Vector2f();
    }

    public void init(long window) {
        glfwSetCursorPosCallback(window, (windowHandle, xpos, ypos) -> {
            currentPos.x = xpos;
            currentPos.y = ypos;
        });
        glfwSetCursorEnterCallback(window, (windowHandle, entered) -> {
            inWindow = entered;
        });
        glfwSetMouseButtonCallback(window, (windowHandle, button, action, mode) -> {
            leftButtonPressed = button == GLFW_MOUSE_BUTTON_1 && action == GLFW_PRESS;
            rightButtonPressed = button == GLFW_MOUSE_BUTTON_2 && action == GLFW_PRESS;
        });
    }

    public Vector2f getDisplayVec() {
        return displayVec;
    }

    public void input(long window) {
        displayVec.x = 0;
        displayVec.y = 0;
        if (previousPos.x > 0 && previousPos.y > 0 && inWindow) {
            double deltaX = currentPos.x - previousPos.x;
            double deltaY = currentPos.y - previousPos.y;
            boolean rotateX = deltaX != 0;
            boolean rotateY = deltaY != 0;
            if (rotateX) {
                displayVec.y = (float) deltaX;
            }
            if (rotateY) {
                displayVec.x = (float) deltaY;
            }
        }
        previousPos.x = currentPos.x;
        previousPos.y = currentPos.y;
    }

    public boolean isLeftButtonPressed() {
        return leftButtonPressed;
    }

    public boolean isRightButtonPressed() {
        return rightButtonPressed;
    }
}
