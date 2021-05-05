package IO;

import java.io.IOException;
import java.io.OutputStream;

public class MyCompressorOutputStream extends OutputStream {

    private OutputStream out;
    public MyCompressorOutputStream(OutputStream outputStream) {
        this.out = outputStream;
    }


    @Override
    public void write(int b) throws IOException {

    }

    @Override
    public void write(byte[] b) throws IOException {
        int index = 0;
        for (int i = 0; i < 6; i++) {
            index = writeMetaData(b, index);
        }

    }
    private int writeMetaData(byte[] b , int index) throws IOException {
        if (b[index] == 0)
        {
            index+=2;
            out.write(0);
            out.write(0);
        }
        else {
            while(b[index]!=0)
            {
                out.write(b[index]);
                index++;
            }
            out.write(b[index]);
            index++;
        }
        return index;
    }