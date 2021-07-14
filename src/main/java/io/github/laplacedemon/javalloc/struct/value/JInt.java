package io.github.laplacedemon.javalloc.struct.value;

import io.github.laplacedemon.javalloc.JStackAlloc;
import io.github.laplacedemon.javalloc.util.UNSAFE;

public class JInt extends JValue {
    public static int SIZE = 4;

    public int get(JStackAlloc jStackAlloc, long handle) {
        byte[] memory = jStackAlloc.getMemory();
        return UNSAFE.getInt(memory, 4 + offset + handle);
    }

    public void set(JStackAlloc jStackAlloc, long handle, int value) {
        byte[] memory = jStackAlloc.getMemory();
        UNSAFE.setInt(memory, 4 + offset + handle, value);
    }
}
