package io.github.laplacedemon.javalloc;

import io.github.laplacedemon.javalloc.struct.value.JStruct;
import io.github.laplacedemon.javalloc.struct.StructMetaspace;
import io.github.laplacedemon.javalloc.util.UNSAFE;

public class JStackAlloc {
    private byte[] data;
    private long top;
    private long top0;
    private StructMetaspace metaspace;

    public JStackAlloc(int size) {
        this.data = new byte[size];
        this.top = 0L;
        this.top0 = this.top;
        this.metaspace = new StructMetaspace();
    }

    public JStackAlloc(byte[] data, int base) {
        this.data = data;
        this.top = base;
        this.top0 = this.top;
        this.metaspace = new StructMetaspace();
    }

    public JStackAlloc(byte[] data, int base, StructMetaspace metaspace) {
        this.data = data;
        this.top = base;
        this.top0 = this.top;
        this.metaspace = metaspace;
    }

    public int sizeOf(Class<? extends JStruct> jStructClass) {
        JStruct classObject = metaspace.getClassObject(jStructClass);
        return classObject.getSize();
    }

    public long alloc(int size) {
        long retTop = top;
        UNSAFE.setInt(data, top, size);
        long tmpTop = top + 4 + size;
        if (tmpTop > data.length) {
            throw new JStackOverflowException();
        }
        this.top = tmpTop;
        return retTop;
    }

    public void free(long p) {
        int size = UNSAFE.getInt(data, p);
        top -= (4 + size);
    }

    public long alloc(JStruct jStruct) {
        int size = jStruct.getSize();
        return alloc(size);
    }

    public <T extends JStruct> T type(Class<T> structClass) {
        return metaspace.getClassObject(structClass);
    }

    public byte[] getMemory() {
        return data;
    }

    public void runBlock(Runnable runnable) {
        long base = top;
        runnable.run();
        top = base;
    }
}
