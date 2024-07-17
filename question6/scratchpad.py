from PIL import Image
import os

directory_path = 'mnist_images'

images = []
for filename in os.listdir(directory_path):
    if filename.endswith(".png"):
        img_path = os.path.join(directory_path, filename)
        images.append(Image.open(img_path))

## Pre-proc image
preprocessed_images = [img.convert('L').resize((28, 28)) for img in images]

## Impl
import numpy as np

image_arrays = np.array([np.array(img) for img in preprocessed_images])
image_arrays = image_arrays.reshape(-1, 28, 28, 1) / 255.0  

## Classify
predictions = model.predict(image_arrays)
predicted_digits = np.argmax(predictions, axis=1)
print(predicted_digits)
