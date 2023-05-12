# StonksAI
Open-source stock prediction experiment (use at your own risk)

StonksAI is an experimental stock prediction neural network created by APCodeZ.

400 of the most commonly traded stocks are converted into a 20px by 20px image with each pixel representing the stock's performance for a day. 

A series of 200 days are stitched together creating a video.

These videos are used to train a generative adversarial network to predict the next few frames after the end of the video, which can be converted back into stock performance.
