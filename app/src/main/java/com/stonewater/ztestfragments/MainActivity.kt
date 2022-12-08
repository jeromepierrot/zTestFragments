package com.stonewater.ztestfragments

import android.app.AlertDialog
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadTextBtn.setOnClickListener{
            val textFragment = TextFragment()
            val manager = supportFragmentManager
            val transaction = manager.beginTransaction()

            transaction.replace(R.id.fragment_container, textFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        loadImageBtn.setOnClickListener{
            val imageFragment = ImageFragment()
            val manager = supportFragmentManager
            val transaction = manager.beginTransaction()

            transaction.replace(R.id.fragment_container, imageFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        exitBtn.setOnClickListener {
            class Exit : DialogFragment(){
                override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
                    val builder = AlertDialog.Builder(getActivity())

                    builder.setTitle("EXIT")

                    builder.setMessage("Do you want to quit this app or show ImageFragment ?")

                    builder.setPositiveButton("EXIT") {
                        dialog,
                        which -> exitProcess(0)
                    }

                    builder.setNegativeButton("Show fragment") {
                        dialog,
                        which -> showImageFragment()
                    }

                    return builder.create()
                }
            }

            this.runOnUiThread(
                Runnable{
                    val manager = supportFragmentManager
                    val transaction  = manager.beginTransaction()
                    val prev = manager.findFragmentByTag("dialog")
                    if (prev != null) {
                        transaction.remove(prev)
                    }
                    val exit = Exit()
                    exit.show(transaction, "dialog")
                }
            )
        }
    }

    private fun showImageFragment() {
        val imageFragment = ImageFragment()
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()

        transaction.replace(R.id.fragment_container, imageFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}