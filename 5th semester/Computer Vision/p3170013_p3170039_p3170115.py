from PIL import Image, ImageDraw, ImageFilter
import numpy as np
from math import sqrt,pi,cos,sin,exp,log
import itertools
from datetime import datetime
#metatroph ths eikonas se monoxromiki(luminance)
def luminance(img):
    res = Image.new("RGB", img.size)
    draw = ImageDraw.Draw(res)
    pixel = img.load()
    width, height = img.width, img.height
    luminance = np.zeros((width, height))
    for x in range(width):
        for y in range(height):
            luminance[x,y] = (.299*pixel[x, y][0])+(.587*pixel[x, y][1])+(.114*pixel[x, y][2])
            color = int(luminance[x,y])
            draw.point((x,y),(color,color,color))
    return res, luminance
#tholoma eikonas
def gaussian_blur(img,colors_img, s,k):
    res = Image.new("RGB", img.size)
    draw = ImageDraw.Draw(res)
    width, height = img.width, img.height
    res_colors = np.zeros((width, height))
    par1 = 2*(s**2)
    par2 = par1*np.pi
    index = 2*k+1
    w=0
    gauss = np.zeros((index, index))
    for x in range(index):
        for y in range(index):
            gauss[x,y] = (1/par2)*exp((-1)*((x-1)**2 + (y-1)**2)/par1)
            w = w +  gauss[x,y]
            
    for x in range(index):
        for y in range(index):
            gauss[x,y] = gauss[x,y] / w
    for x in range(k,width-k):
        for y in range(k,height-k):
            sumi = 0
            for i in range(-k,k):
                for j in range(-k,k):
                        sumi += colors_img[x+i,y+j] *gauss[1+i,1+j]     
            res_colors[x,y] = sumi
            color = sumi
            draw.point((x,y),(int(color),int(color),int(color)))
    return res,res_colors
#kratame mono tis akmes
def sobel(colors_img,thes):
    res = Image.new("RGB", img.size)
    draw = ImageDraw.Draw(res)    
    sobelx = [[-1, -2, -1],[0, 0, 0],[1, 2, 1]]
    sobely = [[-1, 0, 1],[-2, 0, 2],[-1, 0, 1]]    
    width, height = img.width, img.height
    colors = np.zeros((width, height))
    for x in range(1,width-1):
        for y in range(1,height-1):
            sx, sy = 0, 0
            for i in range(3):
                for j in range(3):
                    sx += sobelx[i][j]*colors_img[x+i-1][y+i-1]
                    sy += sobely[i][j]*colors_img[x+i-1][y+i-1]
            color = int(sqrt(sx**2 + sy**2))
            if(color<thes):
                color = 0
            colors[x,y]=color
            draw.point((x, y), (color, color, color))
    return res,colors
#evresh kuklwn
def hough_transform_and_filter(img,colors,radius,s,thes):
    tmp = np.array(colors)
    zerosx, zerosy = np.nonzero(tmp)
    width, height = img.width, img.height
    rd=[radius-s/2,radius+s/2]
    accumulator_buffer = dict(zip(itertools.product(range(width),range(height)) ,(0 for i in range(width*height))))
    shmeia=[]
    for ri in rd:
        for t in range(180):
            shmeia.append((int(ri * cos( 2*t*pi/180 )), int(ri * sin( 2*t*pi/180 )),ri))
    for x,y in zip(zerosx, zerosy):
        for dx, dy, r in shmeia:
            a = x - dx
            b = y - dy
            if(a>=0 and b>=0 and a<width and b<height):
                accumulator_buffer[(a,b)] += 1 #vote
    sorted_accumulator_buffer = sorted(accumulator_buffer.items(), key=lambda item: -item[1])
    circles = []
    for key,value in sorted_accumulator_buffer:
        if(value>thes):
           if(all(sqrt((key[0] - x2) ** 2 + (key[1] - y2) ** 2) > r2 for x2, y2, r2 in circles)):
                circles.append((key[0], key[1],radius))
        else:
            break
    return circles
def show_and_save_image(img, s, circles): 
    colors = [(255,0,0,1), (0,255,0,1), (0,0,255,1), (255,255,0,1)]
    new_image = Image.new("RGB", img.size)
    new_image.paste(img)
    draw_result = ImageDraw.Draw(new_image)
    for c,color in zip(circles, colors):
        for x, y, r in c:
            draw_result.ellipse((x-r, y-r, x+r, y+r),outline = color)
            draw_result.ellipse((x-int(s/2), y-int(s/2), x+int(s/2), y+int(s/2)),fill = color,outline = color)
    new_image.show()
    new_image.save("result.tif")  
def check_circle(x1, y1, x2,y2, r1, r2): 
    distSq = (((x1 - x2)* (x1 - x2))+ ((y1 - y2)* (y1 - y2)))**(.5)
    if(distSq<=r2):
        return True
    if (distSq + r2 == r1):
        return True
    elif (distSq + r2 < r1):
        return True
    else:
        return False    
def edit_circles(circles):
    for i in range(len(circles)):
        for j in range(i+1,len(circles)):
            bigger = circles[i] #px ta 2 evra
            smaller = circles[j] #px ta 1 evra
            for xb,yb,rb in bigger:
                for xs,ys,rs in smaller:
                    if(check_circle(xb,yb,xs,ys,rb,rs)):
                        smaller.remove((xs,ys,rs))
def print_result(circles):
    names=['2-Euro','50-Cent','1-Euro','10-Cent']
    for c,n in zip(circles,names):
        if(len(c)==1):
            verb = 'is'
            coins = 'coin'
        else:
            verb = 'are'
            coins = 'coins'
        print('There ',verb,' ',len(c),' ',n,' ',coins,' in the image')
path = "examples\\coins006.tif"
img = Image.open(path)
print("Change luminance...")
gray_img, luminance = luminance(img)
print("Gaussian Blur...")
r=1
k=2
gaussian_img,colors_gaussian_img = gaussian_blur(gray_img,luminance,r,k)
print("Sobel...")
sobel_img, colors_sobel_img = sobel(colors_gaussian_img,0)
s = 3
thes1 = 346
thes2 = 351
radius = [51, 46.92, 43.35, 38.25]
circles = []
print("Hough Transform...")
print("2 evro:")
circles.append(hough_transform_and_filter(sobel_img,colors_sobel_img,radius[0],s,thes1))
print("50 lepto:")
circles.append(hough_transform_and_filter(sobel_img,colors_sobel_img,radius[1],s,thes2))
print("1 evro:")
circles.append(hough_transform_and_filter(sobel_img,colors_sobel_img,radius[2],s,thes2))
print("10 lepto:")
circles.append(hough_transform_and_filter(sobel_img,colors_sobel_img,radius[3],s,thes2))
edit_circles(circles)
show_and_save_image(gray_img, s, circles)
print_result(circles)
