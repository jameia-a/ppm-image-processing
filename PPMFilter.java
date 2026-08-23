import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
public class PPMFilter {

    public FileInputStream in;
    public FileOutputStream out;

    /**
     * Full PPMFilter constructor.
     *
     * @param filein input P6 PPM file
     * @param fileout output P6 PPM file
     */
    public PPMFilter(FileInputStream fileIn, FileOutputStream fileOut){
        this.in = fileIn;
        this.out = fileOut;
    }

    /**
     * Copies an image in P6 PPM format wihtout changing it.
     */
    public void copyPPM() throws IOException {
        int bufferSize = 100;
        byte[] buffer = new byte[bufferSize];

        int bytesRead = this.in.read(buffer);

        while (bytesRead != -1) {
            this.out.write(buffer, 0, bytesRead);
            bytesRead = this.in.read(buffer);
        }
    }
    /**
     * Copies the first three lines of the P6 PPM header.
     */
    private void copyHeader() throws IOException
    {
        int newLineCount = 0;
        while (newLineCount < 3) {
            int currentByte = this.in.read();
            if (currentByte == -1) {
                throw new IOException("The PPM file has an incomplete header.");
            }
            this.out.write(currentByte);

            if (currentByte == '\n') {
                newLineCount++;
            }
        }
    }
    /**
     * Keeps each pixel's red value and sets green and blue to 0.
     */
    public void redFilter() throws IOException {
        
        copyHeader();
       
        int red = this.in.read();

        while (red != -1) {

            int green = this.in.read();
            int blue = this.in.read();

            if(green == -1 || blue == -1) {
                throw new IOException(" The PPM file has incomplete RGB data.");
            }
            
            this.out.write(red);
            this.out.write(0);
            this.out.write(0);

            red = this.in.read();
        }
    }
    public void greenFilter() throws IOException {

        copyHeader();

        int red = this.in.read(); 

        while (red != -1) {

                int green = this.in.read();
                int blue = this.in.read();

                if (green == -1 || blue == -1) {
                    throw new IOException("The PPM file has incomplete RGB data.");
                }

                this.out.write(0);
                this.out.write(green);
                this.out.write(0);

                red = this.in.read();
            }

        }
        public void blueFilter() throws IOException {
            
            copyHeader();

            int red = this.in.read();

            while (red != -1) {

                int green = this.in.read();
                int blue = this.in.read();

                if (green == -1 || blue == -1) {
                    throw new IOException("The PPM file has incomplete RGB data.");
                }
                this.out.write(0);
                this.out.write(0);
                this.out.write(blue);

                red = this.in.read();
            }
        }
    
    /**
     * Converts every pixel to greyscale using the weighted RGB formula.
     */
    public void greyscaleFilter() throws IOException {

        copyHeader();

        int red = this.in.read();

        while (red != -1) {

            int green = this.in.read();
            int blue = this.in.read();

            if (green == -1 || blue == -1) {
                throw new IOException("The PPM file has incomplete RGB data.");
            }

            int grey = (int) (0.299 * red
                            + 0.587 * green
                            + 0.114 * blue);
            
            this.out.write(grey);
            this.out.write(grey);
            this.out.write(grey);

            red = this.in.read();
        }
    }
    /**
     * Inverts every pixel by subtracting each RGB value from 255.
     */
    public void invertFilter() throws IOException
    {

        copyHeader();

        int red = this.in.read();

        while (red != -1) {

            int green = this.in.read();
            int blue = this.in.read();

            if (green == -1 || blue == -1) {
                throw new IOException("The PPM file has incomplete RGB data");
            }

            this.out.write(255 - red);
            this.out.write(255 - green);
            this.out.write(255 - blue);

            red = this.in.read();
        }
    }
} 
