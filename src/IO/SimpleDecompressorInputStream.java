package IO;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class SimpleDecompressorInputStream extends InputStream {
    private InputStream in;

    public SimpleDecompressorInputStream(InputStream input) {
        this.in = input;
    }

    @Override
    public int read() throws IOException {
        return 0;
    }

    @Override
    public int read(byte[] b) {
        try
        {
            byte[] bCompressed = in.readAllBytes();
            int index =0;
            for(int i = 0 ; i < 6 ; i ++)
            {
                index = readMetaData(bCompressed,index,b);
            }
            int currNum = 1;
            int j = index;
            while(j < bCompressed.length)
            {
                index = readMaze(currNum,bCompressed[j], b, index);
                j++;
                if(currNum == 1)
                    currNum = 0;
                else
                    currNum = 1;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return b.length;
    }


    private int readMetaData(byte[] bCompressed , int index,byte[] b){
        try {


            if (bCompressed[index] == 0) {
                b[index] = bCompressed[index];
                index++;
                b[index] = bCompressed[index];
                index++;
            } else {
                while (bCompressed[index] != 0) {
                    b[index] = bCompressed[index];
                    index++;
                }
                b[index] = bCompressed[index];
                index++;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return index;
    }



    private int readMaze(int currNum, byte count,byte[] b,int index)
    {
        for(int i = 0 ; i < Byte.toUnsignedInt(count); i++)
        {
            b[index] = (byte)currNum;
            index++;
        }
        return index;
    }
}
