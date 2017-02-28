package ch.tkoc.springfx.context

import javafx.util.Builder


class CustomAwareBuilder<T>: Builder<T> {

    override fun build(): T {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}