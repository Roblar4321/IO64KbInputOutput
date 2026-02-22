
class IO(private val inStream: InputStream, private val outStream: OutputStream) {
        private val inBuf = ByteArray(1 shl 16)
        private val outBuf = ByteArray(1 shl 16)
        private var head = 0
        private var tail = 0
        private var outPos = 0

        private fun read(): Int {
            if (head >= tail) {
                head = 0
                tail = inStream.read(inBuf, 0, inBuf.size)
                if (tail <= 0) return -1
            }
            return inBuf[head++].toInt() and 0xFF
        }

        fun nextInt(): Int {
            var c = read()
            while (c <= 32) {
                if (c == -1) return -1
                c = read()
            }
            var res = 0
            while (c > 32) {
                res = res * 10 + (c - '0'.code)
                c = read()
            }
            return res
        }

        fun readGrid(grid: ByteArray, r: Int, c: Int) {
            var idx = 0
            val total = r * c
            while (idx < total) {
                val ch = read()
                if (ch > 32) {
                    grid[idx++] = ch.toByte()
                }
            }
        }

        fun print(b: ByteArray) {
            if (outPos + b.size > outBuf.size) flush()
            System.arraycopy(b, 0, outBuf, outPos, b.size)
            outPos += b.size
        }

        fun flush() {
            if (outPos > 0) {
                outStream.write(outBuf, 0, outPos)
                outPos = 0
            }
        }
}
