package io.github.laplacedemon.javalloc.struct.value;

import io.github.laplacedemon.javalloc.JStackAlloc;
import io.github.laplacedemon.javalloc.util.UNSAFE;

public class JLong extends JValue {
    public static int SIZE = 8;

    public long get(JStackAlloc jStackAlloc, long handle) {
        byte[] memory = jStackAlloc.getMemory();
        return UNSAFE.getLong(memory, 4 + offset + handle);
    }

    public void set(JStackAlloc jStackAlloc, long handle, long value) {
        byte[] memory = jStackAlloc.getMemory();
        UNSAFE.setLong(memory, 4 + offset+ handle, value);
    }
}
