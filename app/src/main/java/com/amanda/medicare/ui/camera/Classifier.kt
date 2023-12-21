package com.amanda.medicare.ui.camera

// Classifier.kt
import android.content.Context
import android.graphics.Bitmap
import org.tensorflow.lite.DataType
import org.tensorflow.lite.Interpreter
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel

class Classifier(context: Context, modelPath: String) {

    private val interpreter: Interpreter
    private val numClasses: Int

    init {
        val model = loadModelFile(context, modelPath)
        interpreter = Interpreter(model)

        val outputTensor = interpreter.getOutputTensor(0)
        numClasses = outputTensor.shape()[1].toInt()
    }

    fun classify(bitmap: Bitmap): Int {
        val inputTensorImage = TensorImage(DataType.FLOAT32)
        inputTensorImage.load(bitmap)

        val inputBuffer = TensorBuffer.createFixedSize(intArrayOf(1, 150, 150, 3), DataType.FLOAT32)
        inputBuffer.loadBuffer(inputTensorImage.buffer)

        val outputBuffer = TensorBuffer.createFixedSize(intArrayOf(1, numClasses), DataType.FLOAT32)

        interpreter.run(inputBuffer.buffer, outputBuffer.buffer.rewind())

        val probabilityArray = outputBuffer.floatArray
        return probabilityArray.indices.maxByOrNull { probabilityArray[it] } ?: -1
    }

    private fun loadModelFile(context: Context, modelPath: String): MappedByteBuffer {
        val fileDescriptor = context.assets.openFd(modelPath)
        val inputStream = fileDescriptor.createInputStream()
        val fileChannel = inputStream!!.channel
        val startOffset = fileDescriptor.startOffset
        val declaredLength = fileDescriptor.declaredLength

        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
    }
}
