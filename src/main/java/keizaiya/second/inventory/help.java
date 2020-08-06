package keizaiya.second.inventory;

import keizaiya.second.file.Admin.adminfile;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class help {
    public static void sendhelp(Integer type, Player player){
        if(type == 0){
            //全体ヘルプ
            player.sendMessage("\n§7========== §e§l全体ヘルプ §7==========\n" +
                    "§7クリックすることで詳細がみれます。");
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),"tellraw " + player.getDisplayName() + gettellrawtocommand(
                    "§bカントリーシステム関連§f .../Country","/Keizaiya help 1"));
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),"tellraw " + player.getDisplayName() + gettellrawtocommand(
                    "§9チャットシステム関連§f .../chatmode","/Keizaiya help 2"));
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),"tellraw " + player.getDisplayName() + gettellrawtocommand(
                    "§b通貨システム関連§f .../Print","/Keizaiya help 3"));
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),"tellraw " + player.getDisplayName() + gettellrawtocommand(
                    "§9通知関連§f .../notice","/Keizaiya help 4"));
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),"tellraw " + player.getDisplayName() + gettellrawtocommand(
                    "§b経済屋Plugin関連§f .../Keizaiya","/Keizaiya help 5"));
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),"tellraw " + player.getDisplayName() + gettellrawtocommand(
                    "§bチャンネル関連§f .../Channel","/Keizaiya help 7"));
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),"tellraw " + player.getDisplayName() + gettellrawtocommand(
                    "§9その他コマンド§f .../selfkick,/Toolcoler,/Playerinfo","/Keizaiya help 6"));
            player.sendMessage("\n§7またwikiにも詳細が載っています");
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),"tellraw " + player.getDisplayName() + gettellrawtoopenurl(
                    "§b§l経済屋公式Wiki","https://sites.google.com/view/keizaiyaserver/home"));
            player.sendMessage("§7==============================");
        }if(type == 1){
            player.sendMessage("\n§7========== §e§lカントリーシステムヘルプ §7==========\n" +
                    "§7クリックすることで詳細が表示、またはコマンドがコピーされます\n");
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),"tellraw " + player.getDisplayName() + gettellrawtosuggest(
                    "§f/Country Create <国名> <国の略称>§b:§7 国を建国します","/country create "));
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),"tellraw " + player.getDisplayName() + gettellrawtosuggest(
                    "§f/Country Invite <プレイヤー>§b:§7 プレイヤーを国に招待します","/country invite "));
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),"tellraw " + player.getDisplayName() + gettellrawtosuggest(
                    "§f/Country caht <役職名> <プレイヤー>§b:§7 指定したプレイヤーの役職名を設定します","/country chat "));
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),"tellraw " + player.getDisplayName() + gettellrawtosuggest(
                    "§f/Country permission <権限名> <プレイヤー>§b:§7 指定した権限をプレイヤーに付与します。権限名はそのまま実行することで確認できます","/country permission "));
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),"tellraw " + player.getDisplayName() + gettellrawtosuggest(
                    "§f/Country remove <Player>§b:§7 プレイヤーを追放又は離脱させます","/country remove "));
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),"tellraw " + player.getDisplayName() + gettellrawtosuggest(
                    "§f/Country promote <Player>§b:§7 元首を指定したプレイヤーに渡します","/country promote "));
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),"tellraw " + player.getDisplayName() + gettellrawtosuggest(
                    "§f/Country Delete§b:§7 国家アカウントを消去します","/country delete "));
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),"tellraw " + player.getDisplayName() + gettellrawtosuggest(
                    "§f/Country nickname <国の略称>§b:§7 国の略称を設定します","/country nickname "));
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),"tellraw " + player.getDisplayName() + gettellrawtosuggest(
                    "§f/Country religoin <宗教名>§b:§7 宗教を設定します(使用には宗教変更カードが必要です)","/country religion "));
            sendtellraw(player,gettellrawtocommand("/country tp§b:§7 カントリーTP関連の詳細を開きます","/keizaiya help 10"));
            sendtellraw(player,gettellrawtocommand("/country item§b:§7 カントリーアイテム関連の詳細を開きます","/keizaiya help 11"));
            player.sendMessage("§7========================================");
        }if(type == 10){
            player.sendMessage("\n§7========== §e§lカントリーTPヘルプ §7==========\n" +
                    "§7クリックすることでコマンドがコピーされます\n");
            sendtellraw(player,gettellrawtosuggest("/country tp set <number>§b:§7 国のテレポート地点を設定します","/country tp set "));
            sendtellraw(player,gettellrawtosuggest("/country tp 1§b:§7 " + adminfile.getTPname() + " にテレポートします" ,"/country tp 1"));
            sendtellraw(player,gettellrawtosuggest("/country tp 2§b:§7 国のTPpoint 0 にテレポートします","/country tp 2"));
            sendtellraw(player,gettellrawtosuggest("/country tp 3§b:§7 国のTPpoint 1 にテレポートします","/country tp 3"));
            player.sendMessage("§7=====================================");
        }if(type == 11){
            player.sendMessage("\n§7========== §e§lカントリーアイテムヘルプ §7==========\n" +
                    "§7クリックすることでコマンドがコピーされます\n");
            sendtellraw(player,gettellrawtosuggest("/country item card <数>§b:§7 国家の証(イデオロギー)をだします","/country item card"));
            sendtellraw(player,gettellrawtosuggest("/country item country <数>§b:§7 国家の証(国家)をだします","/country item country"));
            sendtellraw(player,gettellrawtosuggest("/country item banner§b:§7 手に持っている旗を16個追加します","/country item banner"));
            sendtellraw(player,gettellrawtosuggest("/country item banner set§b:§7 国の国旗を設定します","/country item banner set"));
            sendtellraw(player,gettellrawtosuggest("/country item banner get§b:§7 国の国旗を8個入手します","/country item banner get"));
            player.sendMessage("§7========================================");
        }if(type == 2){
            player.sendMessage("\n§7========== §e§lチャットシステム §7==========\n" +
                    "§7クリックすることでコマンドがコピーされます\n");
            sendtellraw(player,gettellrawtosuggest("/chatmode§b:§7 チャットモードをアカウントと全体で切り替えます","/chatmode"));
            sendtellraw(player,gettellrawtosuggest("/chatmode 1§b:§7 1のアカウントのチャットモードに切り替えます","/chatmode 1"));
            sendtellraw(player,gettellrawtosuggest("/chatmode 2§b:§7 2のアカウントのチャットモードに切り替えます","/chatmode 2"));
            sendtellraw(player,gettellrawtosuggest("/chatmode 3§b:§7 3のアカウントのチャットモードに切り替えます","/chatmode 3"));
            sendtellraw(player,gettellrawtosuggest("/dm <Player> (message)§b:§7 指定したプレイヤーに個人メールを送ります","/dm "));
            sendtellraw(player,gettellrawtosuggest("/w <Player> (message)§b:§7 /dmと同じ","/w "));
            sendtellraw(player,gettellrawtosuggest("/r (message)§b:§7 前回送ったDMの相手にDMを送ります","/r "));
            player.sendMessage("\n・現在のチャットモードはtabを押すと確認することができます");
            player.sendMessage("・文字の先頭に[!]を付けると全体チャットになります");
            player.sendMessage("・文字の先頭に[#]をつけると自動翻訳しなくなります");
            player.sendMessage("・特殊文字は使えます使用する場合は&eなど指定してください");
            player.sendMessage("§7==================================");
        }if(type == 3){
            player.sendMessage("\n§7========== §e§l通貨システム §7==========\n" +
                    "§7クリックすることでコマンドがコピーされます\n");
            sendtellraw(player,gettellrawtosuggest("/print <name>§b:§7 紙をもって実行すると通貨が設定されます","/print"));
            player.sendMessage("このコマンドは権限[item]を持っている必要があります");
            player.sendMessage("§7===============================");
        }if(type == 4){
            player.sendMessage("\n§7========== §e§l通知関連 §7==========\n" +
                    "§7クリックすることでコマンドがコピーされます\n");
            sendtellraw(player,gettellrawtosuggest("/notice§b:§7 現在非表示設定にしてる通知一覧を表示します","/notice"));
            sendtellraw(player,gettellrawtosuggest("/notice list§b:§7 現在表示設定にしている通知一覧を表示します","/notice list"));
            player.sendMessage("§7============================");
        }if(type == 5){

        }if(type == 6){
            player.sendMessage("\n§7========== §e§l通その他システム §7==========\n" +
                    "§7クリックすることでコマンドがコピーされます\n");
            sendtellraw(player,gettellrawtosuggest("/toolcolor§b:§7 現在持っているアイテムの＆を§に変えます","/toolcolor"));
            sendtellraw(player,gettellrawtosuggest("/menu§b:§7 メインメニューを開きます","/menu"));
            sendtellraw(player,gettellrawtosuggest("/selfkicke§b:§7 自分をキックします(わざわざesc押して抜けるのめんd人むけ)","/selfkick"));
            player.sendMessage("§7==================================");
        }if(type == 7){
            player.sendMessage("\n§7========== §e§lチャンネル関連 §7==========\n" +
                    "§7クリックすることでコマンドがコピーされます\n" +
                    "チャンネルに切り替える際はメニューから行ってください");
            sendtellraw(player,gettellrawtosuggest("/channel create <name>§b:§7 チャンネルを作成します","/channel create "));
            sendtellraw(player,gettellrawtosuggest("/channel invite <Player>§b:§7 プレイヤーを招待します","/channel invite "));
            sendtellraw(player,gettellrawtosuggest("/channel invite §b:§7 招待されたチャンネルリストを表示します","/channel invite"));
            sendtellraw(player,gettellrawtosuggest("/channel remove §b:§7 現在開いているチャンネルから退出します。","/channel remove"));
            sendtellraw(player,gettellrawtosuggest("/channel remove <Player>§b:§7 指定したプレイヤーを退出させます","/channel remove "));
            sendtellraw(player,gettellrawtosuggest("/channel close §b:§7 現在開いているチャンネルを閉じます。","/channel close"));
            player.sendMessage("§7============================");
        }if(type == 7269){
            player.sendMessage("メニューのどこかを~~~回クリックしよう");
        }
    }
    public static String gettellrawtocommand(String message , String command){
        return " {\"text\":\"" + message + "\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"" + command + "\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"§3Click here\"}}";
    }

    public static String gettellrawtoopenurl(String message , String URL){
        return " {\"text\":\"" + message + "\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"" + URL + "\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"§3Open URL\"}}";
    }

    public static String gettellrawtosuggest(String message , String suggest){
        return " {\"text\":\"" + message + "\",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"" + suggest + "\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"§3get Command\"}}";
    }

    public static void sendtellraw(Player player,String json){
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),"tellraw " + player.getDisplayName() + json);
    }
}
