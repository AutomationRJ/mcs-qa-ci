import os
from os import listdir
from os.path import isfile,join
import re
import shutil
def f(x):
	return x[1]

#r_path = r"/root/.jenkins/jobs/RCS2-Webservices/htmlreports/HTML_Report"
r_path = r"/root/.jenkins/workspace/RCS2-Webservices/test-output"
r_path1 = r"/root/.jenkins/workspace"
onlyfiles = [(f,os.path.getctime(join(r_path, f))) for f in listdir(r_path) if isfile(join(r_path, f)) and re.search(".*RCS2 API Result.*",f)]
shutil.copy2(join(r_path,sorted(onlyfiles,key=f)[-1][0]),join(r_path,"index.html"))
