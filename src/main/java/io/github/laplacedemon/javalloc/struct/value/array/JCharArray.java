package io.github.laplacedemon.javalloc.struct.value.array;

import io.github.laplacedemon.javalloc.JStackAlloc;
import io.github.laplacedemon.javalloc.util.UNSAFE;

public class JCharArray extends JArray {
    public static int ELEMENT_SIZE = 2;

    public char get(JStackAlloc jStackAlloc, int index, long handle) {
        byte[] memory = jStackAlloc.getMemory();
        return UNSAFE.getChar(memory, 4 + offset + index * 2 + handle);
    }

    public void set(JStackAlloc jStackAlloc, long handle, int index, char value) {
        byte[] memory = jStackAlloc.getMemory();
        UNSAFE.setChar(memory, 4 + offset + index * 2 + handle, value);
    }
}
