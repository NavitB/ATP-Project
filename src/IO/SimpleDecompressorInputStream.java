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
    public int read(byte[] b) throws IOException {
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
        return b.length;
    }

//    @Override
//    public int read() throws IOException {
//        ArrayList<Byte> b = new ArrayList<>();
//        int input = in.read();
//        if (input != -1) {
//            //read the metadata
//            for (int i = 0; i < 6; i++) {
//                b.addAll(readMetaData(input));
//                input = in.read();
//            }
//        }
//        int currNum = 1;
//        while(input != -1)
//        {
//            b.addAll(readMaze(currNum,input);
//            input = in.read();
//        }
//    }
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


//    private ArrayList<Byte> readMetaData( int input) throws IOException {
//        ArrayList<Byte> b = new ArrayList<>();
//        if (input == 0) {
//            b.add((byte) input);
//            b.add((byte) in.read());
//        } else {
//            while (input != 0) {
//                b.add((byte) input);
//                input = in.read();
//            }
//            b.add((byte) input);
//        }
//        return b;
//
//    }
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
