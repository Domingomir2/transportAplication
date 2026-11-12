@echo off
set GRAFANA_HOME=C:\Users\domingo.miralles\Downloads\grafana_13.3.1_42849527790_windows_amd64\grafana-13.3.1
"%GRAFANA_HOME%\bin\grafana.exe" server --homepath="%GRAFANA_HOME%" --config="%~dp0custom.ini"