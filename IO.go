package main

import (
    "bufio"
    "os"
)

func main() {
    const bufSize = 1 << 20
    reader := bufio.NewReaderSize(os.Stdin, bufSize)
    writer := bufio.NewWriterSize(os.Stdout, bufSize)
    defer writer.Flush()


    readInt := func() int {
        x := 0
        for {
            b, _ := reader.ReadByte()
            if b >= '0' && b <= '9' {
                x = int(b - '0')
                break
            }
        }
        for {
            b, _ := reader.ReadByte()
            if b < '0' || b > '9' {
                break
            }
            x = x*10 + int(b-'0')
        }
        return x
    }

    r, c := readInt(), readInt()
    n := r * c

    grid := make([]byte, n)
    for i := 0; i < n; i++ {
        for {
            b, _ := reader.ReadByte()
                grid[i] = b
                break
        }
    }
}
