static class IO {
        private final InputStream in;
        private final OutputStream out;
        

        private final byte[] inBuf = new byte[1 << 16]; 
        private final byte[] outBuf = new byte[1 << 16]; 
        
        private int head = 0, tail = 0;
        private int outPos = 0;

        public IO(InputStream in, OutputStream out) {
            this.in = in;
            this.out = out;
        }

        private int read() throws Exception {
            if (head >= tail) {
                head = 0;
                tail = in.read(inBuf, 0, inBuf.length);
                if (tail <= 0) return -1;
            }
            return inBuf[head++];
        }

        public int nextInt() throws Exception {
            int c = read();
            while (c <= 32) {
                if (c == -1) return -1;
                c = read();
            }
            int res = 0;
            while (c > 32) {
                res = res * 10 + c - '0';
                c = read();
            }
            return res;
        }

        public void readGrid(byte[] grid, int r, int c) throws Exception {
            int idx = 0;
            int total = r * c;
            while (idx < total) {
                int ch = read();
        
                if (ch > 32) {
                    grid[idx++] = (byte) ch;
                }
            }
        }


        public void print(byte[] b) throws Exception {
            if (outPos + b.length > outBuf.length) {
                flush();
            }
            System.arraycopy(b, 0, outBuf, outPos, b.length);
            outPos += b.length;
        }

        public void flush() throws Exception {
            if (outPos > 0) {
                out.write(outBuf, 0, outPos);
                outPos = 0;
            }
        }
}
