/*
 * Copyright (c) 2017 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.android.forgetmenot

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent



class MainActivity : AppCompatActivity() {

  private val taskList: MutableList<String> = mutableListOf()
  private val adapter by lazy { makeAdapter(taskList) }
  private val ADD_TASK_REQUEST = 1


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    taskListView.adapter = adapter

    taskListView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id -> }
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    // 1
    if (requestCode == ADD_TASK_REQUEST) {
      // 2
      if (resultCode == Activity.RESULT_OK) {
        // 3
        val task = data?.getStringExtra(TaskDescriptionActivity.EXTRA_TASK_DESCRIPTION)
        task?.let {
          taskList.add(task)
          // 4
          adapter.notifyDataSetChanged()
        }
      }
    }
  }


  fun addTaskClicked(view: View) {
    val intent = Intent(this, TaskDescriptionActivity::class.java)
    startActivityForResult(intent, ADD_TASK_REQUEST)


  }

  private fun makeAdapter(list: List<String>): ArrayAdapter<String> =
      ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
}
