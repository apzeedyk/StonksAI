package main.java.stonks;

import java.io.File;

public class NeuralNetwork {
	NeuronLayer inputLayer;
	NeuronLayer[] hiddenLayers;
	NeuronLayer outputLayer;
	
	public NeuralNetwork(int inputLayerSize, int numHiddenLayers, int hiddenLayerSize, int outputLayerSize) {
		this.inputLayer = new NeuronLayer(inputLayerSize);
		this.hiddenLayers = new NeuronLayer[numHiddenLayers];
		for (int i = 0; i < numHiddenLayers; i++) {
			hiddenLayers[i] = new NeuronLayer(hiddenLayerSize);
		}
		this.outputLayer = new NeuronLayer(outputLayerSize);
	}
	
	public void train (File data) {
				
	}
}