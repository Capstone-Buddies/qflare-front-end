package com.dicoding.capstone.custom

//import android.content.Context
//import android.text.Editable
//import android.text.TextWatcher
//import android.util.AttributeSet
//import androidx.appcompat.widget.AppCompatEditText
//import com.dicoding.capstone.R
//
//class passwordText : AppCompatEditText {
//
//    private var errorMessage: String = ""
//
//    constructor(context: Context) : super(context) {
//        init()
//    }
//
//    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
//        init()
//        initAttributes(context, attrs)
//    }
//
//    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
//        init()
//        initAttributes(context, attrs)
//    }
//
//    private fun init() {
//        addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
//
//            override fun afterTextChanged(s: Editable?) {
//                val password = s.toString()
//                if (password.length < 8) {
//                    // Jika panjang password kurang dari 8 karakter, tampilkan pesan kesalahan
//                    error = errorMessage
//                } else {
//                    // Jika panjang password sudah memenuhi syarat, hilangkan pesan kesalahan
//                    error = null
//                }
//            }
//        })
//    }
//
//    private fun initAttributes(context: Context, attrs: AttributeSet) {
//        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.PasswordEditText)
//        errorMessage = typedArray.getString(R.styleable.PasswordEditText_errorMessage)
//            ?: "Password must be at least 8 characters long"
//        typedArray.recycle()
//    }
//}