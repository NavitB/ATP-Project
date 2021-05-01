package IO;

import java.io.IOException;
import java.io.InputStream;

public class SimpleDecompressorInputStream extends InputStream {
    private InputStream in;

    public SimpleDecompressorInputStream(InputStream input) {
        this.in = input;
    }

    @Override
    public int read() throws IOException {
        return 0;
    }


}
