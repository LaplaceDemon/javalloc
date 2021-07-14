package io.github.laplacedemon.javalloc.struct.value;

import io.github.laplacedemon.javalloc.JStackAlloc;
import io.github.laplacedemon.javalloc.util.UNSAFE;

public class JBoolean extends JValue {
    public static final int SIZE = 1;

    public boolean get(JStackAlloc jStackAlloc, long handle) {
        byte[] memory = jStackAlloc.getMemory();
        return UNSAFE.getBoolean(memory, 4 + offset + handle);
    }

    public void set(JStackAlloc jStackAlloc, long handle, boolean value) {
        byte[] memory = jStackAlloc.getMemory();
        UNSAFE.setBoolean(memory, 4 + offset + handle, value);
    }

}
