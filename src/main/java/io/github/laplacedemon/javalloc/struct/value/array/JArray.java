package io.github.laplacedemon.javalloc.struct.value.array;

import io.github.laplacedemon.javalloc.struct.value.JValue;

public class JArray extends JValue {
    protected int length;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
