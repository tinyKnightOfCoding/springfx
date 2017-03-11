package ch.tkoc.springfx.context

import javafx.scene.Node
import javafx.util.Builder


open class JavaFxBeanBuilder : Builder<Node> {

    lateinit var bean: Node

    override fun build(): Node = bean
}