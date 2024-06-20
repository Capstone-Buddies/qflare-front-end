package com.dicoding.capstone.custom

//import android.content.Context
//import android.text.Editable
//import android.text.TextWatcher
//import android.util.AttributeSet
//import androidx.appcompat.widget.AppCompatEditText
//import com.dicoding.capstone.R
//
//class usernameText : AppCompatEditText {
//
//    constructor(context: Context) : super(context) {
//        init()
//    }
//
//    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
//        init()
//    }
//
//    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
//        context,
//        attrs,
//        defStyleAttr
//    ) {
//        init()
//    }
//
//    private fun init() {
//        addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
//
//            override fun afterTextChanged(s: Editable?) {
//                val username = s.toString()
//                when {
//                    username.isEmpty() -> error = context.getString(R.string.username_empty)
//                    username.length < 5 -> error = context.getString(R.string.username_too_short)
//                    !username.matches(Regex("^[a-zA-Z0-9_]*$")) -> error = context.getString(R.string.username_invalid_characters)
//                    else -> error = null
//                }
//            }
//        })
//    }
//}