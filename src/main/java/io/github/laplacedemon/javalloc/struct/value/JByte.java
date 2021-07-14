package io.github.laplacedemon.javalloc.struct.value;

import io.github.laplacedemon.javalloc.JStackAlloc;
import io.github.laplacedemon.javalloc.util.UNSAFE;

public class JByte extends JValue {
    public static final int SIZE = 1;

    public byte get(JStackAlloc jStackAlloc, long handle) {
        byte[] memory = jStackAlloc.getMemory();
        return UNSAFE.getByte(memory, 4 + offset + handle);
    }

    public void set(JStackAlloc jStackAlloc, long handle, byte value) {
        byte[] memory = jStackAlloc.getMemory();
        UNSAFE.setByte(memory, 4 + offset + handle, value);
    }
}
