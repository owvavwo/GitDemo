from Tkinter import*

root=Tk()

def callback(event):
    print('���µļ���',event.char,event.keysym,event.keycode)
    


frame=Frame(root,width=200,height=200)
frame.bind('<Key>',callback)
frame.focus_set()
frame.pack()

mainloop()
