# VCF2CSV

This tiny small little program extracts the names and the telephone numbers from a vcf file and put the data (Arabic only) into a csv (comma separated value) file. This application outputs the data in the following format: name, telephone #1, telephone #2, ...

The reason for creating the application is that my grandfather wanted a paper listing all the names and phones from his phone. And his phone converted the names which were in Arabic to strings of UTF-8 QP (Unicode quoted-printable). Hence, only Arabic data are extracted.

## How to use the applicatoin?
Of course, you have the source code here.  However, you can directly use the jar file as follows:

java -jar VCF2CSV.jar convert src [dst] [-r]

You can find that jar file in the *dist* directory under *VCF2CSV* directory.

### Arguments explanation
you pass *convert* command to perform conversion. Then you pass the *src* file path (including the file name). No need to mention that this argument isn't optional. You can include the file extension or ignore it, both are fine. After that, you **optionally** pass the *dst* argument, which is the destination you want the file. This can be directory (indicated by ending the path with a slash - */*), path including file name without extension or with extension. If this parameter isn't passed, the same name and directory of the source file are used (with the extension being csv). The last **optionally** accepted argument is the *-r* which means reverse. This makes the output names be reversed in order: meaning: lastname firstname instead of firstname lastname.
