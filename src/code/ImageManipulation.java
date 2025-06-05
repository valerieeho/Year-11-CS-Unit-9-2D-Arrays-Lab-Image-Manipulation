package code;

import image.Pixel;
import image.APImage;

public class ImageManipulation {

    /** CHALLENGE 0: Display Image
     *  Write a statement that will display the image in a window
     */
    public static void main(String[] args) {
        APImage picture = new APImage("cyberpunk2077.jpg");
        picture.draw();

        reflectImage("cyberpunk2077.jpg");
        edgeDetection("cyberpunk2077.jpg", 20);
        grayScale("cyberpunk2077.jpg");
        blackAndWhite("cyberpunk2077.jpg");

    }

    /** CHALLENGE ONE: Grayscale
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: a grayscale copy of the image
     *
     * To convert a colour image to grayscale, we need to visit every pixel in the image ...
     * Calculate the average of the red, green, and blue components of the pixel.
     * Set the red, green, and blue components to this average value. */
    public static void grayScale(String pathOfFile) {
        APImage picture = new APImage(pathOfFile);
        for (int i = 0; i < picture.getHeight(); i++)
        {
            for (int j = 0; j < picture.getWidth(); j++)
            {
                Pixel pixel = picture.getPixel(j,i);
                int average = getAverageColour(pixel);
                pixel.setRed(average);
                pixel.setGreen(average);
                pixel.setBlue(average);
            }
        }
        picture.draw();
    }

    /** A helper method that can be used to assist you in each challenge.
     * This method simply calculates the average of the RGB values of a single pixel.
     * @param pixel
     * @return the average RGB value
     */
    private static int getAverageColour(Pixel pixel) {
        return (pixel.getRed() + pixel.getBlue() + pixel.getGreen())/3;
    }

    /** CHALLENGE TWO: Black and White
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: a black and white copy of the image
     *
     * To convert a colour image to black and white, we need to visit every pixel in the image ...
     * Calculate the average of the red, green, and blue components of the pixel.
     * If the average is less than 128, set the pixel to black
     * If the average is equal to or greater than 128, set the pixel to white */
    public static void blackAndWhite(String pathOfFile) {
        APImage picture = new APImage(pathOfFile);
        for (int i = 0; i < picture.getHeight(); i++)
        {
            for (int j = 0; j < picture.getWidth(); j++)
            {
                Pixel pixel = picture.getPixel(j,i);
                int average = getAverageColour(pixel);

                if (average >= 128)
                {
                    pixel.setRed(255);
                    pixel.setGreen(255);
                    pixel.setBlue(255);
                }

                else
                {
                    pixel.setRed(0);
                    pixel.setGreen(0);
                    pixel.setBlue(0);
                }
            }
        }
        picture.draw();

    }

    /** CHALLENGE Three: Edge Detection
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: an outline of the image. The amount of information will correspond to the threshold.
     *
     * Edge detection is an image processing technique for finding the boundaries of objects within images.
     * It works by detecting discontinuities in brightness. Edge detection is used for image segmentation
     * and data extraction in areas such as image processing, computer vision, and machine vision.
     *
     * There are many different edge detection algorithms. We will use a basic edge detection technique
     * For each pixel, we will calculate ...
     * 1. The average colour value of the current pixel
     * 2. The average colour value of the pixel to the left of the current pixel
     * 3. The average colour value of the pixel below the current pixel
     * If the difference between 1. and 2. OR if the difference between 1. and 3. is greater than some threshold value,
     * we will set the current pixel to black. This is because an absolute difference that is greater than our threshold
     * value should indicate an edge and thus, we colour the pixel black.
     * Otherwise, we will set the current pixel to white
     * NOTE: We want to be able to apply edge detection using various thresholds
     * For example, we could apply edge detection to an image using a threshold of 20 OR we could apply
     * edge detection to an image using a threshold of 35
     *  */
    public static void edgeDetection(String pathToFile, int threshold) {
        APImage picture = new APImage(pathToFile);
        APImage newPicture = new APImage(picture.getWidth(), picture.getHeight());

        for (int i = 0; i < picture.getHeight()-1; i++)
        {
            for (int j = 1; j < picture.getWidth(); j++)
            {
                Pixel newPixel = newPicture.getPixel(j,i);
                Pixel pixel = picture.getPixel(j,i);
                int current = getAverageColour(pixel);
                Pixel leftPixel = picture.getPixel(j-1,i);
                int left = getAverageColour(leftPixel);
                Pixel lowerPixel = picture.getPixel(j,i+1);
                int lower = getAverageColour(lowerPixel);
                if (Math.abs(current - left) > threshold || Math.abs(current - lower) > threshold)
                {
                    newPixel.setRed(0);
                    newPixel.setGreen(0);
                    newPixel.setBlue(0);
                }
                else
                {
                    newPixel.setBlue(255);
                    newPixel.setGreen(255);
                    newPixel.setRed(255);
                }
            }
        }
        newPicture.draw();
    }

    /** CHALLENGE Four: Reflect Image
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: the image reflected about the y-axis
     *
     */
    public static void reflectImage(String pathToFile) {
        APImage picture = new APImage(pathToFile);
        APImage newPicture = new APImage(picture.getWidth(), picture.getHeight());
        for (int i = 0; i < picture.getHeight(); i++)
        {
            for (int j = 0; j < picture.getWidth(); j++)
            {
                Pixel reversePix = picture.getPixel(picture.getWidth()-(j+1),i);
                Pixel newPixel = newPicture.getPixel(j,i);
                int green = reversePix.getGreen();
                int blue = reversePix.getBlue();
                int red = reversePix.getRed();
                newPixel.setGreen(green);
                newPixel.setBlue(blue);
                newPixel.setRed(red);
            }
        }
        newPicture.draw();
    }

    /** CHALLENGE Five: Rotate Image
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: the image rotated 90 degrees CLOCKWISE
     *
     *  */
    public static void rotateImage(String pathToFile) {

    }

}