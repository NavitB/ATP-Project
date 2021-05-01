package IO;

import java.io.IOException;
import java.io.OutputStream;

public class SimpleCompressorOutputStream extends OutputStream {

    private OutputStream out;
    public SimpleCompressorOutputStream(OutputStream outputStream) {
        this.out = outputStream;
    }

    @Override
    public void write(int b) throws IOException {

    }

    @Override
    public void write(byte[] b) throws IOException {
        int index =0;
        for(int i = 0 ; i <6 ; i ++)
        {
            index = writeMetaData(b,index);
        }
        int currentNum =1;
        while (index<b.length)
        {
            if(b[index]==currentNum)
            {
                index=writeMaze(b, index, currentNum);
            }
            else {
                if(currentNum==1)
                    currentNum =0;
                else
                    currentNum=1;
            }
        }
    }

    private int writeMaze(byte[] b , int index, int currentNum) throws IOException {
        int sum =0;
        while(b[index]==currentNum)
        {
            sum++;
            index++;
        }
        if(sum<=255)
        {
            out.write(sum);
            return index;
        }
        else {
            while (sum>255)
            {
                out.write(255);
                out.write(0);
                sum-=255;

            }
            if (sum>=0)
            {
                out.write(sum);
                return index;
            }
        }
        return index;
    }

    private int writeMetaData(byte[] b , int index) throws IOException {
        if (b[index] == 0)
        {
            index+=2;
            out.write(0);
            out.write(0);
        }
        else
        {
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
}
