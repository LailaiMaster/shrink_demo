package com.example.demo_plugin

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.TextUtils
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity

class TestActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        val iv = findViewById<android.widget.ImageView>(R.id.iv)
        intent.getStringExtra("res_path")?.let {
            if (!TextUtils.isEmpty(it)) {
                iv.setImageDrawable(getDrawableX(it, this))

            }
        }
    }

    companion object {
        @JvmStatic
        fun start(context: Context, res_pat: String) {
            val starter = Intent(context, TestActivity::class.java)
            starter.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            starter.putExtra("res_path", res_pat)
            context.startActivity(starter)
        }
    }

    private fun getDrawableX(resPath: String?, context: Context): Drawable? {
        var drawable: Drawable? = null
        val rid: Int
        try {
            if (!TextUtils.isEmpty(resPath)) {
                rid = context.resources.getIdentifier(resPath, "drawable", context.packageName)
                drawable = ContextCompat.getDrawable(context, rid)
            }

        } catch (e: Exception) {
        }
        return drawable
    }
}