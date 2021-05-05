package IO;

import java.io.IOException;
import java.io.InputStream;

public class MyDecompressorInputStream extends InputStream {
    private InputStream in;

    public MyDecompressorInputStream(InputStream in) {
        this.in = in;
    }

    @Override
    public int read() throws IOException {
        return 0;
    }

    @Override
    public int read(byte[] b) throws IOException {
        byte[] bCompressed = in.readAllBytes();
        int index =0;
        int rows = 0,cols = 0;
        index = readMetaData(bCompressed,index,b);
        for(int i=0; i < index; i++)
        {
            rows += Byte.toUnsignedInt((Byte)bCompressed[i]);
        }
        int tempIndex = index;
        index = readMetaData(bCompressed,index,b);
        for(int i = tempIndex ; i < index; i++)
        {
            cols += Byte.toUnsignedInt((Byte)bCompressed[i]);
        }
        index = 0;
        int mazeLength = rows * cols;
        for(int i = 0 ; i < 6 ; i ++)
        {
            index = readMetaData(bCompressed,index,b);
        }
        readMaze(bCompressed,index,b,mazeLength);
        return b.length;
    }

    private void readMaze(byte[] bCompressed, int index, byte[] b,int mazeLength) {
        String binaryNum = "";
        int tempIndex = 0;
        for(int i = index; i < bCompressed.length-1; i++)
        {
            int num = Byte.toUnsignedInt(bCompressed[i]);
            binaryNum = String.format("%8s", Integer.toBinaryString(num)).replaceAll(" ","0");
            for(int j = 0; j<binaryNum.length(); j++)
            {
                b[index+tempIndex] =(byte)Integer.parseInt(String.valueOf(binaryNum.charAt(j)));
                tempIndex++;
            }
        }
        int lastNumLength = mazeLength - tempIndex;
        if(lastNumLength > 0)
        {
            int num = Byte.toUnsignedInt(bCompressed[bCompressed.length-1]);
            binaryNum = String.format("%" + lastNumLength + "s", Integer.toBinaryString(num)).replaceAll(" ","0");
            for(int j = 0; j<binaryNum.length(); j++)
            {
                b[index+tempIndex+j] =(byte)Integer.parseInt(String.valueOf(binaryNum.charAt(j)));
            }

        }

    }

    private int readMetaData(byte[] bCompressed , int index,byte[] b) throws IOException {
        if (bCompressed[index] == 0)
        {
            b[index] = bCompressed[index];
            index++;
            b[index] = bCompressed[index];
            index++;
        }
        else
        {
            while(bCompressed[index]!=0)
            {
                b[index] = bCompressed[index];
                index++;
            }
            b[index] = bCompressed[index];
            index++;
        }
        return index;
    }
}
