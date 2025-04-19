import numpy as np #Linear algebra and mathematical operations
import pandas as pd #importing and loading data
from sklearn.preprocessing import OneHotEncoder
import os
iris_df = pd.read_csv(r"C:\Users\prevo\OneDrive\Documents\GitHub\CSC-212-Data-Structs-Alg-Labs\Final-Project\Iris.csv")
iris_df = iris_df.sample(frac=1).reset_index(drop=True) # Shuffle

X = iris_df[['SepalLengthCm', 'SepalWidthCm', 'PetalLengthCm', 'PetalWidthCm']]
X = np.array(X)
X[:5]


one_hot_encoder = OneHotEncoder(sparse_output=False)
Y = iris_df.Species
Y = one_hot_encoder.fit_transform(np.array(Y).reshape(-1, 1))
Y[:5]

from sklearn.model_selection import train_test_split
X_train, X_test, Y_train, Y_test = train_test_split(X, Y, test_size=0.15)
X_train, X_val, Y_train, Y_val = train_test_split(X_train, Y_train, test_size=0.1)

def NeuralNetwork(X_train, Y_train, X_val=None, Y_val=None, epochs=10,   nodes=[], lr=0.15):
    hidden_layers = len(nodes) - 1
    weights = InitializeWeight(nodes)

    for epoch in range(1, epochs+1):
        weights = Train(X_train, Y_train, lr, weights)

        if(epoch % 20 == 0):
            print("Epoch {}".format(epoch))
            print("Training Accuracy:{}".format(Accuracy(X_train, Y_train, weights)))
            if X_val.any():
                print("Validation Accuracy:{}".format(Accuracy(X_val, Y_val, weights)))
            
    return weights

def InitializeWeight(nodes):
    layers, weights = len(nodes), []
    
    for i in range(1, layers):
        w = [[np.random.uniform(-1, 1) for j in range(nodes[i-1] + 1)]
              for k in range(nodes[i])]
        weights.append(np.matrix(w))
    
    return weights

def ForwardPropagation(x, weights, layers):
    activations, layer_input = [x], x
    for j in range(layers):
        activation = Sigmoid(np.dot(layer_input, weights[j].T))
        activations.append(activation)
        layer_input = np.append(1, activation)
    
    return activations


def BackPropagation(y, activations, weights, layers,lr):
    outputFinal = activations[-1]
    error = np.matrix(y - outputFinal) # Error after 1 cycle
    
    for j in range(layers, 0, -1):
        currActivation = activations[j]
        
        if(j > 1):
            # Append previous
            prevActivation = np.append(1, activations[j-1])
        else:
            # First hidden layer
            prevActivation = activations[0]
        
        delta = np.multiply(error, SigmoidDerivative(currActivation))
        weights[j-1] += lr * np.multiply(delta.T, prevActivation)

        wc = np.delete(weights[j-1], [0], axis=1)
        error = np.dot(delta, wc) #current layer error
    
    return weights


def Train(X, Y, lr, weights):
    layers = len(weights)
    for i in range(len(X)):
        x, y = X[i], Y[i]
        x = np.matrix(np.append(1, x))
        
        activations = ForwardPropagation(x, weights, layers)
        weights = BackPropagation(y, activations, weights, layers,lr)

    return weights


def Sigmoid(x):
    return 1 / (1 + np.exp(-x))

def SigmoidDerivative(x):
    return np.multiply(x, 1-x)

def Predict(item, weights):
    layers = len(weights)
    item = np.append(1, item)
    
    # Forward prop.
    activations = ForwardPropagation(item, weights, layers)
    
    Foutput = activations[-1].A1
    index = FindMaxActivation(Foutput)

    y = [0 for j in range(len(Foutput))]
    y[index] = 1 

    return y 


def FindMaxActivation(output):
    m, index = output[0], 0
    for i in range(1, len(output)):
        if(output[i] > m):
            m, index = output[i], i
    
    return index


def Accuracy(X, Y, weights):
    correct = 0

    for i in range(len(X)):
        x, y = X[i], list(Y[i])
        guess = Predict(x, weights)

        if(y == guess):
            # Right prediction
            correct += 1

    return correct / len(X)


f = len(X[0]) # no. of features
o = len(Y[0]) # no. of classes

layers = [f, 5, 10, o] # no. of nodes 
L, E = 0.15, 100

weights = NeuralNetwork(X_train, Y_train, X_val, Y_val, epochs=E, 
nodes=layers, lr=L)

print("Testing Accuracy: {}".format(Accuracy(X_test, Y_test, weights)))
input("Press Enter to exit...")
