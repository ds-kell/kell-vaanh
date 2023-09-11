package vn.com.kell.vaanh.base.adapter


import android.view.View
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.RecyclerView


open class Holder<MODEL>(view: View) : RecyclerView.ViewHolder(view) {
    protected var model: MODEL? = null
        private set

    @CallSuper
    open fun onBind(position: Int, model: MODEL?) {
        this.model = model
    }

}