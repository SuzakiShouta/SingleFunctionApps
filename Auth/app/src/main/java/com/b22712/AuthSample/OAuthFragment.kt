package com.b22712.AuthSample

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth

class OAuthFragment : Fragment() {
    companion object {
        private const val RC_SIGN_IN = 123
        private const val TAG = "OAuthFragment"
        // 動的にFragmentを生成するためにインスタンスを返す関数をcompanion objectに書く
        fun newInstance() = OAuthFragment()
    }

    private lateinit var firebaseAuth: FirebaseAuth
    private var listener: OnTokenReceivedListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_o_auth, container, false)

        firebaseAuth = FirebaseAuth.getInstance()

        val signInButton = view.findViewById<com.google.android.gms.common.SignInButton>(R.id.sign_in_button)
        signInButton.setOnClickListener {
            startSignInFlow()
        }

        return view
    }

    private fun startSignInFlow() {
        val providers = arrayListOf(
            AuthUI.IdpConfig.GoogleBuilder().build(),
        )

        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build(),
            RC_SIGN_IN
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)
            if (resultCode == Activity.RESULT_OK) {
                val user = firebaseAuth.currentUser
                Log.d(TAG, "Sign in success: ${user?.displayName}")

                val token = user?.getIdToken(true)
                    ?.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val idToken = task.result?.token
                            // トークンはここで確認します．
                            Log.d(TAG, "Token: $idToken")
                            // リスナにトークンを渡す
                            listener?.onTokenReceived(idToken)
                        } else {
                            Log.e(TAG, "Failed to retrieve token")
                        }
                    }

            } else {
                // リクエスト失敗
                Log.d(TAG, "Sign in failed: ${response?.error?.errorCode}")
            }
        }
    }

    // これを継承したクラスはonTokenReceivedをオーバーライドしてコールバックを受け取る
    interface OnTokenReceivedListener {
        fun onTokenReceived(token: String?)
    }
    fun setOnTokenReceivedListener(listener: OnTokenReceivedListener) {
        this.listener = listener
    }

}
