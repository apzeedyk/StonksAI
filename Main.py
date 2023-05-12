# Import libraries
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import matplotlib.animation as animation
import pandas_datareader as web

# Define constants
N = 400 # Number of companies
D = 200 # Number of days
S = 20 # Size of the video frame in pixels

# Get the list of top 400 most valuable stocks from Yahoo and their symbols
yahoo = pd.read_html("https://en.wikipedia.org/wiki/List_of_S%26P_500_companies")[0]
symbols = yahoo["Symbol"].head(N).tolist()

start_date = "2020-01-01"
end_date = "2020-12-31"
data2 = web.DataReader(name="UBER", data_source="stooq", start=start_date, end=end_date)
print(data2)

# Get the closing percent change for each company over the last D days
data = pd.DataFrame()

for symbol in symbols:
    try:
        df = pd.read_csv(f"https://query1.finance.yahoo.com/v7/finance/download/{symbol}?period1={int((pd.Timestamp.today() - pd.Timedelta(days=D)).timestamp())}&period2={int(pd.Timestamp.today().timestamp())}&interval=1d&events=history&includeAdjustedClose=true")
        data[symbol] = df["Close"].pct_change()
    except:
        data[symbol] = np.nan

print(data)
data = data.copy()
# Drop any columns with missing values
data.dropna(axis=0)
print(data)


# Normalize the data to the range [0, 1]
data = (data - data.min()) / (data.max() - data.min())

# Convert the data into a 3D array of shape (D, S, S)
video = np.zeros((D, S, S))
for i in range(1, 138):
    # Reshape the data for day i into a square matrix of size S x S
    video[i] = data.iloc[i].values.reshape((S, S))

# Create a figure and an axes for the animation
print("help")
fig, ax = plt.subplots()
ax.set_title("Closing percent change for top 400 most valuable stocks")
ax.set_xticks([])
ax.set_yticks([])

# Create an image object to display the video frames
im = ax.imshow(video[0], cmap="RdYlGn", vmin=0, vmax=1)

# Define a function to update the image object for each frame
def animate(i):
    im.set_data(video[i])
    return im,

# Create an animation object using the update function and save it as a video file
ani = animation.FuncAnimation(fig, animate, frames=D, interval=50, blit=True)
ani.save("video.mp4")
