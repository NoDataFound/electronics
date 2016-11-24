
package org.onebeartoe.rpi.rgb.led.matrix;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Roberto Marquez <https://www.youtube.com/user/onebeartoe>
 * 
 */
public class RaspberryPiRgbLedMatrixHat 
{
    private Logger logger;
    
    private Process commandProcess;
    
    public RaspberryPiRgbLedMatrixHat()
    {
        logger = Logger.getLogger(getClass().getName());
    }
    
    /**
     * Here is the original command to show an animation:
     * 
     *      ./led-image-viewer --led-no-hardware-pulse --led-gpio-mapping=adafruit-hat -l200 ../../bubbles.gif
     */
    public void startAnimationCommand() throws IOException, InterruptedException
    {
        String executable = "/home/pi/rpi-rgb-led-matrix/utils/led-image-viewer";
        int loopCount = 20;
        String loopParameter = "-l" + loopCount;
        String gifPath = "/home/pi/bubbles.gif";
        String [] list = {executable, "--led-no-hardware-pulse", "--led-gpio-mapping=adafruit-hat", loopParameter, gifPath, "", ""};
        List<String> command = Arrays.asList(list);
        
        logger.log(Level.INFO, "staring animation process");
        ProcessBuilder builder = new ProcessBuilder(command);

        commandProcess = builder.start();
                
        logger.log(Level.INFO, "waiting for animation process");
        int waitValue = commandProcess.waitFor();
        
        logger.log(Level.INFO, "animation process wait over");
    }
    
    /**
     * This sends a destroy message to the process, stopping execution.
     */
    public void stopCommand()
    {
        commandProcess.destroy();
    }
}
