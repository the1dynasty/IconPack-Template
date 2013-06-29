Current Version 2.1.1

This template works with most launchers... But it is a template and you should test individually before making claims 
to support any specific launcher!

Thanks for using my source for your Icon Pack base... I hope this helps you build a complete Icon Pack for various 
launchers... I've left commented lines throughout the files... Feel free to read them to get a better understanding 
of the code and what does what!! Some things NEED to be changed... So please read on and at the very least, do these 
things FIRST!!!

REQUIREMENTS:
Some basic code knowledge (I do explain a lot to make it easy for no0bs), also a basic understanding of Icon Packs 
and how they work in terms of the files you need to edit/add ie: knowing these essential files need to be updated to 
match your own icons needs appfilter.xml, icon_pack.xml, drawable.xml... I have included my own files of each which 
are great bases to start from.

Step 1:
Download ActionBarSherlock (will be referred to as ABS from now on) if you haven't already done so yet 
https://github.com/JakeWharton/ActionBarSherlock 

Step 2:
Import this project, ABS. Then add ABS to Icon Pack Template as its dependent library.
(Importing on Eclipse - Right click on the template folder and select Properties>Android>Select 4.2.2>Under that,
remove any bad imports and add ABS>Apply>OK)

Step 3:
Open AndroidManifest.xml and change line 22. Then read the comment starting on line 54. You can experiment with those 
two activities depending on how you want the app to look. Next scroll all the way down to line 157 and change that 
part to match your package name. You are done with the AndroidManifest.xml you can now close this.

Step 4:
Open the “src” directory and find the “your.icons.name.here” package. Refractor that to match your own package name. 
You shouldn’t, but if you get errors on some of your java files, you will need to fix the imports on the files that 
show an error.

Now the fun begins :D

Step 5:
Depending on how you set up your base determined in Step 3 referencing line 54 of your AndroidManifest.xml, will 
determine what you edit next. If you left it alone, navigate to the src>gridview package and open Main.java also navigate
to src>adapters and open MainAdapter.java. 
OR 
If you changed that line in the AndroidManifest.xml to 
android:name="your.icons.name.here.AppActivity" then navigate to the your.icons.name.here package and open 
AppActivity.java. 
In either case, all of the files have been commented thoroughly enough for you to understand 
exactly what you need to edit. Failure to make these edits will result in an app that is nearly identical to my own app. With links back to my profiles and apps instead of yours.

Step 6:
Navigate to res>values>strings.xml read the comments there and make the necessary edits.

Step 7:
Navigate to res>values>colors.xml you can edit these or leave them alone how you see fit. This is where you go to 
invert most of the app if you want and edit the colors for theme skins like text color etc.

Almost done!!!

Step 8:
Navigate to res>drawable-nodpi and edit the wallpaper and image previews.

Step 9:
Navigate to res>mipmap-hdpi and mipmap-xhdpi and edit that image with your own or leave my pretty face there :D

Step 10:
Add your own icons with the size of 144x144px to the res>drawable-xxhdpi folder. You can use other sizes if you want, 
added to the other folders too. But this is where I put mine.

Step 11:
If you do NOT want to invert this app or change colors, move to next step... Everyone else, navigate back to 
res>values>colors.xml... This is where you change all of your app backgrounds and text colors... Move to next step 
if you’re using the flatview look... For listview, just a few more things to edit... Next, navigate to the 
res>layout>listview_layout.xml file... Read the comment on line 27.

Step 12:
Now with your inverted/normal look finished, your icons added, your names & descriptions changed, you misc images 
edited... I think you are done... Export it and start sharing with the world!!


Any other edits to this are completely up to you... I hope you realize that a lot of time went into this to not only 
make it easy for you to make an icon pack, but for you to actually understand the changes to code you are making 
here... Please consider sharing this info with anyone that wants to make their own icon pack...


I am also looking for some people that wish to contribute to the code... There are a few things we can add to this 
to make it better!! If you would like to contribute, send me an email the1dynasty.android@gmail.com I’m sure everyone 
would appreciate your contribution too!!
