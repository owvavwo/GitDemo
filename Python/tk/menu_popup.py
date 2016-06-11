from tkinter import*
root = Tk()

def callback():
    print('Hello!')
menubar = Menu(root)
menubar.add_command(label='undo',command=callback)
menubar.add_command(label='repeat',command=root.quit)

frame=Frame(root,width=512,height=512)
frame.pack()

def popup(event):
    menubar.post(event.x_root,event.y_root)
    
frame.bind('<Button-3>',popup)

mainloop()